package jp.kaleidot725.adbpad.ui.screen.device

import jp.kaleidot725.adbpad.core.mvi.MVI
import jp.kaleidot725.adbpad.core.mvi.mvi
import jp.kaleidot725.adbpad.domain.usecase.device.UpdateDevicesUseCase
import jp.kaleidot725.adbpad.ui.screen.device.state.DeviceAction
import jp.kaleidot725.adbpad.ui.screen.device.state.DeviceSideEffect
import jp.kaleidot725.adbpad.ui.screen.device.state.DeviceState
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class DeviceStateHolder(
    private val updateDevicesUseCase: UpdateDevicesUseCase,
) : MVI<DeviceState, DeviceAction, DeviceSideEffect> by mvi(initialUiState = DeviceState(emptyList())) {
    private var deviceJob: Job? = null

    override fun onSetup() {
        collectDevices()
    }

    override fun onRefresh() {
        collectDevices()
    }

    override fun onDispose() {
        coroutineScope.cancel()
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
    }
}
