package jp.kaleidot725.adbpad.ui.screen.menu.command

import jp.kaleidot725.adbpad.domain.model.command.NormalCommand
import jp.kaleidot725.adbpad.domain.model.command.NormalCommandCategory
import jp.kaleidot725.adbpad.domain.model.command.NormalCommandGroup
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.usecase.command.ExecuteCommandUseCase
import jp.kaleidot725.adbpad.domain.usecase.command.GetNormalCommandGroup
import jp.kaleidot725.adbpad.domain.usecase.device.GetSelectedDeviceFlowUseCase
import jp.kaleidot725.adbpad.ui.common.ChildStateHolder
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
    private val getNormalCommandGroup: GetNormalCommandGroup,
    private val executeCommandUseCase: ExecuteCommandUseCase,
    private val getSelectedDeviceFlowUseCase: GetSelectedDeviceFlowUseCase,
) : ChildStateHolder<CommandState> {
    private val coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main + Dispatchers.IO)
    private val commands: MutableStateFlow<NormalCommandGroup> = MutableStateFlow(NormalCommandGroup.Empty)
    private val filtered: MutableStateFlow<NormalCommandCategory?> = MutableStateFlow(null)
    private val selectedDevice: StateFlow<Device?> =
        getSelectedDeviceFlowUseCase()
            .stateIn(coroutineScope, SharingStarted.WhileSubscribed(), null)

    override val state: StateFlow<CommandState> =
        combine(
            commands,
            filtered,
            selectedDevice,
        ) { commands, filtered, selectedDevice ->
            CommandState(commands, filtered, selectedDevice)
        }.stateIn(coroutineScope, SharingStarted.WhileSubscribed(), CommandState())

    override fun setup() {
        commands.value = getNormalCommandGroup()
    }

    override fun refresh() {
        commands.value = getNormalCommandGroup()
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
                onStart = { commands.value = getNormalCommandGroup() },
                onFailed = { commands.value = getNormalCommandGroup() },
                onComplete = { commands.value = getNormalCommandGroup() },
            )
        }
    }

    fun clickTab(filtered: NormalCommandCategory?) {
        this.filtered.value = filtered
    }
}
