package jp.kaleidot725.adbpad.repository.impl

import com.malinskiy.adam.AndroidDebugBridgeClientFactory
import com.malinskiy.adam.request.device.AsyncDeviceMonitorRequest
import jp.kaleidot725.adbpad.domain.model.Device
import jp.kaleidot725.adbpad.domain.model.DeviceState
import jp.kaleidot725.adbpad.domain.repository.DeviceRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow

class DeviceRepositoryImpl : DeviceRepository {
    override fun getDeviceFlow(coroutineScope: CoroutineScope): Flow<List<Device>> {
        val adbClient = AndroidDebugBridgeClientFactory().build()
        val receiveChannel = adbClient.execute(
            request = AsyncDeviceMonitorRequest(),
            scope = coroutineScope
        )
        val receiveFlow = receiveChannel.receiveAsFlow()
        return receiveFlow.map { rowDevices -> rowDevices.map { Device(it.serial, it.state.convert()) } }
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
