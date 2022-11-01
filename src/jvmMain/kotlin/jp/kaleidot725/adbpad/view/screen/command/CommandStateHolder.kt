package jp.kaleidot725.adbpad.view.screen.command

import jp.kaleidot725.adbpad.domain.model.Command
import jp.kaleidot725.adbpad.domain.model.Device
import jp.kaleidot725.adbpad.domain.model.Event
import jp.kaleidot725.adbpad.domain.model.Language
import jp.kaleidot725.adbpad.domain.usecase.command.ExecuteCommandUseCase
import jp.kaleidot725.adbpad.domain.usecase.command.GetCommandList
import jp.kaleidot725.adbpad.domain.usecase.device.GetSelectedDeviceFlowUseCase
import jp.kaleidot725.adbpad.view.common.ChildStateHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CommandStateHolder(
    val getCommandList: GetCommandList,
    val executeCommandUseCase: ExecuteCommandUseCase,
    val getSelectedDeviceFlowUseCase: GetSelectedDeviceFlowUseCase
) : ChildStateHolder<CommandState> {
    private val coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main + Dispatchers.IO)
    private val commands: MutableStateFlow<List<Command>> = MutableStateFlow(emptyList())
    private val selectedDevice: StateFlow<Device?> = getSelectedDeviceFlowUseCase()
        .stateIn(coroutineScope, SharingStarted.WhileSubscribed(), null)

    override val state: StateFlow<CommandState> = combine(
        commands,
        selectedDevice,
    ) { commands, selectedDevice ->
        CommandState(commands, selectedDevice)
    }.stateIn(coroutineScope, SharingStarted.WhileSubscribed(), CommandState())

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()
    override val event: SharedFlow<Event> = _event

    override fun setup() {
        commands.value = getCommandList()
    }

    override fun dispose() {
        coroutineScope.cancel()
    }

    fun executeCommand(command: Command) {
        val selectedDevice = state.value.selectedDevice ?: return
        coroutineScope.launch {
            executeCommandUseCase(
                device = selectedDevice,
                command = command,
                onStart = {
                    _event.emit(Event.StartCommand(command.title))
                    commands.value = getCommandList()
                },
                onFailed = {
                    commands.value = getCommandList()
                    _event.emit(Event.ErrorCommand(command.title, Language.COMMAND_UNKNOWN_ERROR))
                },
                onComplete = {
                    commands.value = getCommandList()
                    _event.emit(Event.EndCommand(command.title))
                }
            )
        }
    }
}
