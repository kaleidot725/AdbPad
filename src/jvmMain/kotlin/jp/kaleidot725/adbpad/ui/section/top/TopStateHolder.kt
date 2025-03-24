package jp.kaleidot725.adbpad.ui.section.top

import jp.kaleidot725.adbpad.core.mvi.MVI
import jp.kaleidot725.adbpad.core.mvi.mvi
import jp.kaleidot725.adbpad.domain.model.command.DeviceControlCommand
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.usecase.command.ExecuteDeviceControlCommandUseCase
import jp.kaleidot725.adbpad.domain.usecase.device.GetSelectedDeviceFlowUseCase
import jp.kaleidot725.adbpad.domain.usecase.device.SelectDeviceUseCase
import jp.kaleidot725.adbpad.domain.usecase.device.UpdateDevicesUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class TopStateHolder(
    private val updateDevicesUseCase: UpdateDevicesUseCase,
    private val getSelectedDeviceFlowUseCase: GetSelectedDeviceFlowUseCase,
    private val selectDeviceUseCase: SelectDeviceUseCase,
    private val executeDeviceControlCommandUseCase: ExecuteDeviceControlCommandUseCase,
) : MVI<TopState, TopAction, TopSideEffect> by mvi(initialUiState = TopState()) {
    private var deviceJob: Job? = null
    private var selectedDeviceJob: Job? = null

    override fun onSetup() {
        collectDevices()
    }

    override fun onRefresh() {
        collectDevices()
    }

    override fun onDispose() {
        coroutineScope.cancel()
    }

    override fun onAction(uiAction: TopAction) {
        coroutineScope.launch {
            when (uiAction) {
                is TopAction.ExecuteCommand -> executeCommand(uiAction.command)
                is TopAction.SelectDevice -> selectDevice(uiAction.device)
            }
        }
    }

    private suspend fun selectDevice(device: Device) {
        selectDeviceUseCase(device)
    }

    private suspend fun executeCommand(command: DeviceControlCommand) {
        val device = currentState.selectedDevice ?: return
        executeDeviceControlCommandUseCase(
            device = device,
            command = command,
        )
    }

    private fun collectDevices() {
        deviceJob?.cancel()
        deviceJob =
            coroutineScope.launch {
                while (isActive) {
                    val devices = updateDevicesUseCase()
                    update { this.copy(devices = devices) }
                    delay(1000)
                }
            }

        selectedDeviceJob?.cancel()
        selectedDeviceJob =
            coroutineScope.launch {
                getSelectedDeviceFlowUseCase().collect {
                    update { this.copy(selectedDevice = it) }
                }
            }
    }
}
