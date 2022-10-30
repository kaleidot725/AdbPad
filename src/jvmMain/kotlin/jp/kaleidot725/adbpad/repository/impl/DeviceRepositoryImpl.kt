package jp.kaleidot725.adbpad.repository.impl

import com.malinskiy.adam.AndroidDebugBridgeClientFactory
import com.malinskiy.adam.request.device.AsyncDeviceMonitorRequest
import jp.kaleidot725.adbpad.domain.model.Device
import jp.kaleidot725.adbpad.domain.model.DeviceState
import jp.kaleidot725.adbpad.domain.repository.DeviceRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.CONFLATED
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class DeviceRepositoryImpl : DeviceRepository {
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    private val adbClient = AndroidDebugBridgeClientFactory().build()

    private var lastSelectedDevice: Device? = null
    private val selectedDevice get() = Channel<Device?>(CONFLATED)

    private var lastDevices: List<Device> = emptyList()
    private val devicesChannel
        get() = adbClient.execute(
            request = AsyncDeviceMonitorRequest(),
            scope = coroutineScope
        )

    init {
        coroutineScope.launch {
            devicesChannel.receiveAsFlow().collect { rowDevices ->
                lastDevices = rowDevices.convert()
                val contains = lastDevices.contains(lastSelectedDevice)
                if (!contains) {
                    lastSelectedDevice = lastDevices.firstOrNull()
                    selectedDevice.send(lastSelectedDevice)
                }
            }
        }
    }

    override suspend fun selectDevice(device: Device): Boolean {
        return if (lastDevices.contains(device)) {
            lastSelectedDevice = device
            selectedDevice.send(device)
            true
        } else {
            false
        }
    }

    override fun getDeviceFlow(): Flow<List<Device>> {
        return devicesChannel.receiveAsFlow().map { rowDevices -> rowDevices.convert() }
    }

    override fun getSelectedDeviceFlow(): Flow<Device?> {
        return selectedDevice.receiveAsFlow()
    }

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
