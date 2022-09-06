package jp.kaleidot725.adbpad

import com.malinskiy.adam.interactor.StartAdbInteractor
import com.malinskiy.adam.request.device.Device
import jp.kaleidot725.adbpad.model.usecase.GetDevicesFlowUseCase
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

class MainStateHolder(
    val getDevicesFlowUseCase: GetDevicesFlowUseCase = GetDevicesFlowUseCase()
) {
    private val coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main + Dispatchers.IO)
    private val devices: MutableStateFlow<List<Device>> = MutableStateFlow(emptyList())
    private val selectedDevice: MutableStateFlow<Device?> = MutableStateFlow(null)
    val state: StateFlow<MainState> = combine(devices, selectedDevice) { devices, selectedDevice ->
        MainState(devices = devices, selectedDevice = selectedDevice)
    }.stateIn(coroutineScope, SharingStarted.WhileSubscribed(), MainState())

    fun setup() {
        coroutineScope.launch {
            StartAdbInteractor()
            getDevicesFlowUseCase(coroutineScope).collect {
                devices.value = it
                val hasNotDevice = !it.contains(selectedDevice.value)
                if (hasNotDevice) selectedDevice.value = it.firstOrNull()
            }
        }
    }

    fun selectDevice(device: Device) {
        selectedDevice.value = device
    }

    fun dispose() {
        coroutineScope.cancel()
    }
}
