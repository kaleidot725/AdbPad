package jp.kaleidot725.adbpad.ui.section

import jp.kaleidot725.adbpad.domain.model.command.DeviceControlCommand
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.usecase.command.ExecuteDeviceControlCommandUseCase
import jp.kaleidot725.adbpad.domain.usecase.device.GetSelectedDeviceFlowUseCase
import jp.kaleidot725.adbpad.domain.usecase.device.SelectDeviceUseCase
import jp.kaleidot725.adbpad.domain.usecase.device.UpdateDevicesUseCase
import jp.kaleidot725.adbpad.ui.common.ChildStateHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class TopStateHolder(
    private val updateDevicesUseCase: UpdateDevicesUseCase,
    private val getSelectedDeviceFlowUseCase: GetSelectedDeviceFlowUseCase,
    private val selectDeviceUseCase: SelectDeviceUseCase,
    private val executeDeviceControlCommandUseCase: ExecuteDeviceControlCommandUseCase,
) : ChildStateHolder<TopState> {
    private val coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main + Dispatchers.IO)
    private var deviceJob: Job? = null
    private val _devices: MutableStateFlow<List<Device>> = MutableStateFlow(emptyList())
    private val devices: StateFlow<List<Device>> = _devices.asStateFlow()

    private var selectedDeviceJob: Job? = null
    private val _selectedDevice: MutableStateFlow<Device?> = MutableStateFlow(null)
    private val selectedDevice: StateFlow<Device?> = _selectedDevice.asStateFlow()

    override val state: StateFlow<TopState> =
        combine(devices, selectedDevice) { devices, selectedDevice ->
            TopState(
                devices = devices,
                selectedDevice = selectedDevice,
            )
        }.stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = TopState(),
        )

    override fun setup() {
        collectDevices()
    }

    override fun refresh() {
        collectDevices()
    }

    override fun dispose() {
        coroutineScope.cancel()
    }

    fun selectDevice(device: Device) {
        coroutineScope.launch {
            selectDeviceUseCase(device)
        }
    }

    fun executeCommand(command: DeviceControlCommand) {
        val device = selectedDevice.value ?: return
        coroutineScope.launch {
            executeDeviceControlCommandUseCase(
                device = device,
                command = command,
            )
        }
    }

    private fun collectDevices() {
        deviceJob?.cancel()
        deviceJob =
            coroutineScope.launch {
                while (isActive) {
                    _devices.value = updateDevicesUseCase()
                    delay(1000)
                }
            }

        selectedDeviceJob?.cancel()
        selectedDeviceJob =
            coroutineScope.launch {
                getSelectedDeviceFlowUseCase().collect {
                    _selectedDevice.value = it
                }
            }
    }
}
