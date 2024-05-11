package jp.kaleidot725.adbpad.repository.impl

import com.malinskiy.adam.AndroidDebugBridgeClientFactory
import com.malinskiy.adam.request.device.AsyncDeviceMonitorRequest
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.model.device.DeviceState
import jp.kaleidot725.adbpad.domain.repository.DeviceRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class DeviceRepositoryImpl : DeviceRepository {
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    private val adbClient = AndroidDebugBridgeClientFactory().build()

    private var lastSelectedDevice: Device? = null
    private val selectedDevice: MutableSharedFlow<Device?> = MutableSharedFlow(replay = 1)

    init {
        coroutineScope.launch {
            createDevicesFlow().collect { devices ->
                if (!devices.contains(lastSelectedDevice)) {
                    lastSelectedDevice = devices.firstOrNull()
                    selectedDevice.emit(lastSelectedDevice)
                }
            }
        }
    }

    override suspend fun selectDevice(device: Device): Boolean {
        lastSelectedDevice = device
        selectedDevice.emit(device)
        return true
    }

    override fun getDeviceFlow(): Flow<List<Device>> {
        return createDevicesFlow()
    }

    override fun getSelectedDeviceFlow(): Flow<Device?> {
        return selectedDevice.asSharedFlow()
    }

    private fun createDevicesFlow() =
        adbClient.execute(
            request = AsyncDeviceMonitorRequest(),
            scope = coroutineScope,
        ).receiveAsFlow().map { rowDevices -> rowDevices.convert() }

    private fun List<com.malinskiy.adam.request.device.Device>.convert(): List<Device> {
        return map { Device(it.serial, it.state.convert()) }
    }

    private fun com.malinskiy.adam.request.device.DeviceState.convert(): DeviceState {
        return when (this) {
            com.malinskiy.adam.request.device.DeviceState.OFFLINE -> DeviceState.OFFLINE
            com.malinskiy.adam.request.device.DeviceState.BOOTLOADER -> DeviceState.BOOTLOADER
            com.malinskiy.adam.request.device.DeviceState.DEVICE -> DeviceState.DEVICE
            com.malinskiy.adam.request.device.DeviceState.HOST -> DeviceState.HOST
            com.malinskiy.adam.request.device.DeviceState.RECOVERY -> DeviceState.RECOVERY
            com.malinskiy.adam.request.device.DeviceState.RESCUE -> DeviceState.RESCUE
            com.malinskiy.adam.request.device.DeviceState.SIDELOAD -> DeviceState.SIDELOAD
            com.malinskiy.adam.request.device.DeviceState.UNAUTHORIZED -> DeviceState.UNAUTHORIZED
            com.malinskiy.adam.request.device.DeviceState.AUTHORIZING -> DeviceState.AUTHORIZING
            com.malinskiy.adam.request.device.DeviceState.CONNECTING -> DeviceState.CONNECTING
            com.malinskiy.adam.request.device.DeviceState.UNKNOWN -> DeviceState.UNKNOWN
        }
    }
}
