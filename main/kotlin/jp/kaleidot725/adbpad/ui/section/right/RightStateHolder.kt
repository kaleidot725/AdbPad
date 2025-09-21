package jp.kaleidot725.adbpad.ui.section.right

import jp.kaleidot725.adbpad.core.mvi.MVIBase
import jp.kaleidot725.adbpad.domain.model.command.DeviceControlCommand
import jp.kaleidot725.adbpad.domain.usecase.command.ExecuteDeviceControlCommandUseCase
import jp.kaleidot725.adbpad.domain.usecase.device.GetSelectedDeviceFlowUseCase
import jp.kaleidot725.adbpad.domain.usecase.scrcpy.LaunchScrcpyUseCase
import jp.kaleidot725.adbpad.ui.section.right.state.RightAction
import jp.kaleidot725.adbpad.ui.section.right.state.RightSideEffect
import jp.kaleidot725.adbpad.ui.section.right.state.RightState
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RightStateHolder(
    private val getSelectedDeviceFlowUseCase: GetSelectedDeviceFlowUseCase,
    private val executeDeviceControlCommandUseCase: ExecuteDeviceControlCommandUseCase,
    private val launchScrcpyUseCase: LaunchScrcpyUseCase,
) : MVIBase<RightState, RightAction, RightSideEffect>(RightState()) {
    private var selectedDeviceJob: Job? = null

    override fun onSetup() {
        collectSelectedDevice()
    }

    override fun onRefresh() {
        collectSelectedDevice()
    }

    override fun onAction(uiAction: RightAction) {
        coroutineScope.launch {
            when (uiAction) {
                is RightAction.ExecuteCommand -> executeCommand(uiAction.command)
                RightAction.LaunchScrcpy -> launchScrcpy()
                RightAction.ShowScrcpyControl -> showScrcpyControl()
                RightAction.HideScrcpyControl -> hideScrcpyControl()
            }
        }
    }

    private suspend fun executeCommand(command: DeviceControlCommand) {
        val device = currentState.selectedDevice ?: return
        executeDeviceControlCommandUseCase(
            device = device,
            command = command,
        )
    }

    private suspend fun launchScrcpy() {
        val device = currentState.selectedDevice ?: return
        try {
            val success = launchScrcpyUseCase(device)
            if (success) {
                showScrcpyControl()
            }
        } catch (e: Exception) {
            println("Failed to launch Scrcpy: ${e.message}")
        }
    }

    private fun showScrcpyControl() {
        update { copy(isScrcpyControlVisible = true) }
    }

    private fun hideScrcpyControl() {
        update { copy(isScrcpyControlVisible = false) }
    }

    private fun collectSelectedDevice() {
        selectedDeviceJob?.cancel()
        selectedDeviceJob =
            coroutineScope.launch {
                getSelectedDeviceFlowUseCase().collect { device ->
                    update { copy(selectedDevice = device) }
                }
            }
    }
}
