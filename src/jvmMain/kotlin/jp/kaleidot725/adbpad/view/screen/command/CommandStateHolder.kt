package jp.kaleidot725.adbpad.view.screen.command

import jp.kaleidot725.adbpad.domain.model.command.NormalCommand
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.usecase.command.ExecuteCommandUseCase
import jp.kaleidot725.adbpad.domain.usecase.command.GetCommandList
import jp.kaleidot725.adbpad.domain.usecase.device.GetSelectedDeviceFlowUseCase
import jp.kaleidot725.adbpad.view.common.ChildStateHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CommandStateHolder(
    private val getCommandList: GetCommandList,
    private val executeCommandUseCase: ExecuteCommandUseCase,
    private val getSelectedDeviceFlowUseCase: GetSelectedDeviceFlowUseCase,
) : ChildStateHolder<CommandState> {
    private val coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main + Dispatchers.IO)
    private val commands: MutableStateFlow<List<NormalCommand>> = MutableStateFlow(emptyList())
    private val selectedDevice: StateFlow<Device?> =
        getSelectedDeviceFlowUseCase()
            .stateIn(coroutineScope, SharingStarted.WhileSubscribed(), null)

    override val state: StateFlow<CommandState> =
        combine(
            commands,
            selectedDevice,
        ) { commands, selectedDevice ->
            CommandState(commands, selectedDevice)
        }.stateIn(coroutineScope, SharingStarted.WhileSubscribed(), CommandState())

    override fun setup() {
        commands.value = getCommandList()
    }

    override fun refresh() {
        commands.value = getCommandList()
    }

    override fun dispose() {
        coroutineScope.cancel()
    }

    fun executeCommand(command: NormalCommand) {
        val selectedDevice = state.value.selectedDevice ?: return
        coroutineScope.launch {
            executeCommandUseCase(
                device = selectedDevice,
                command = command,
                onStart = { commands.value = getCommandList() },
                onFailed = { commands.value = getCommandList() },
                onComplete = { commands.value = getCommandList() },
            )
        }
    }
}
