package jp.kaleidot725.adbpad.view.screen.command

import com.malinskiy.adam.request.device.Device
import jp.kaleidot725.adbpad.domain.model.Command
import jp.kaleidot725.adbpad.domain.model.Event
import jp.kaleidot725.adbpad.domain.usecase.command.ExecuteCommandUseCase
import jp.kaleidot725.adbpad.domain.usecase.command.GetNotRunningCommandList
import jp.kaleidot725.adbpad.domain.usecase.command.GetRunningCommandList
import jp.kaleidot725.adbpad.view.common.ChildStateHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CommandStateHolder(
    val getNotRunningCommandList: GetNotRunningCommandList = GetNotRunningCommandList(),
    val getRunningCommandList: GetRunningCommandList = GetRunningCommandList(),
    val executeCommandUseCase: ExecuteCommandUseCase = ExecuteCommandUseCase()
) : ChildStateHolder<CommandState> {
    private val coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main + Dispatchers.IO)
    private val commands: MutableStateFlow<List<Command>> = MutableStateFlow(emptyList())
    private val selectedDevice: MutableStateFlow<Device?> = MutableStateFlow(null)
    override val state: StateFlow<CommandState> = combine(
        commands,
        selectedDevice,
    ) { commands, selectedDevice ->
        CommandState(commands, selectedDevice)
    }.stateIn(coroutineScope, SharingStarted.WhileSubscribed(), CommandState())

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()
    override val event: SharedFlow<Event> = _event

    override fun setup() {
        commands.value = getNotRunningCommandList()
    }

    override fun dispose() {
        coroutineScope.cancel()
    }

    private val runningCommandSets = mutableSetOf<Command>()
    fun executeCommand(device: Device, command: Command) {
        coroutineScope.launch {
            _event.emit(Event("${command.title} コマンド実行開始"))
            runningCommandSets.add(command)
            commands.value = getRunningCommandList(runningCommandSets.toList())

            executeCommandUseCase(device.serial, command)
            delay(300)

            runningCommandSets.remove(command)
            commands.value = getRunningCommandList(runningCommandSets.toList())
            _event.emit(Event("${command.title} コマンド実行終了"))
        }
    }
}
