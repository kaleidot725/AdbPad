package jp.kaleidot725.adbpad.ui.screen.device

import jp.kaleidot725.adbpad.core.mvi.MVIBase
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.repository.DeviceRepository
import jp.kaleidot725.adbpad.domain.usecase.device.UpdateDevicesUseCase
import jp.kaleidot725.adbpad.ui.screen.device.state.DeviceAction
import jp.kaleidot725.adbpad.ui.screen.device.state.DeviceSideEffect
import jp.kaleidot725.adbpad.ui.screen.device.state.DeviceState
import kotlinx.coroutines.launch

class DeviceStateHolder(
    private val deviceRepository: DeviceRepository,
    private val updateDevicesUseCase: UpdateDevicesUseCase,
) : MVIBase<DeviceState, DeviceAction, DeviceSideEffect>(initialUiState = DeviceState(emptyList())) {
    override fun onSetup() {
        coroutineScope.launch {
            val devices = updateDevicesUseCase()
            update {
                this.copy(
                    devices = devices,
                    isUpdating = false,
                )
            }
        }
    }

    override fun onRefresh() {}

    override fun onAction(uiAction: DeviceAction) {
        coroutineScope.launch {
            when (uiAction) {
                DeviceAction.Close -> close()
                DeviceAction.Save -> save()
                is DeviceAction.UpdateDeviceName -> updateDeviceName(uiAction.device, uiAction.name)
            }
        }
    }

    private fun close() {
        sideEffect(DeviceSideEffect.Close)
    }

    private suspend fun save() {
        update { copy(isUpdating = true) }
        deviceRepository.saveDevices(currentState.devices)
        sideEffect(DeviceSideEffect.Close)
    }

    private fun updateDeviceName(
        device: Device,
        name: String,
    ) {
        update {
            val targetIndex = this.devices.indexOfFirst { it.serial == device.serial }
            val newDevices = this.devices.toMutableList()
            newDevices[targetIndex] = this.devices[targetIndex].copy(name = name)
            this.copy(devices = newDevices)
        }
    }
}
