package jp.kaleidot725.adbpad.ui.screen.device

import jp.kaleidot725.adbpad.core.mvi.MVI
import jp.kaleidot725.adbpad.core.mvi.mvi
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.repository.DeviceRepository
import jp.kaleidot725.adbpad.domain.usecase.device.UpdateDevicesUseCase
import jp.kaleidot725.adbpad.ui.screen.device.state.DeviceAction
import jp.kaleidot725.adbpad.ui.screen.device.state.DeviceSideEffect
import jp.kaleidot725.adbpad.ui.screen.device.state.DeviceState
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class DeviceStateHolder(
    private val deviceRepository: DeviceRepository,
    private val updateDevicesUseCase: UpdateDevicesUseCase,
) : MVI<DeviceState, DeviceAction, DeviceSideEffect> by mvi(initialUiState = DeviceState(emptyList())) {
    private var deviceJob: Job? = null

    override fun onSetup() {
        getDevices()
    }

    override fun onRefresh() {
        getDevices()
    }

    override fun onDispose() {
        coroutineScope.cancel()
    }

    override fun onAction(uiAction: DeviceAction) {
        coroutineScope.launch {
            when (uiAction) {
                DeviceAction.Close -> close()
                DeviceAction.Save -> save()
                is DeviceAction.UpdateDeviceName -> updateDeviceName(uiAction.device, uiAction.name)
            }
        }
    }

    private suspend fun close() {
        sideEffect(DeviceSideEffect.Close)
    }

    private suspend fun save() {
        update { copy(isUpdating = true) }
        deviceRepository.saveDevices(currentState.devices)
        sideEffect(DeviceSideEffect.Close)
    }

    private suspend fun updateDeviceName(
        device: Device,
        name: String,
    ) {
        val targetIndex = currentState.devices.indexOfFirst { it.name == device.name }
        val newDevice = currentState.devices[targetIndex].copy(name = name)
        val newDevices = currentState.devices.toMutableList().apply { set(targetIndex, newDevice) }
        update { this.copy(devices = newDevices) }
    }

    private fun getDevices() {
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
}
