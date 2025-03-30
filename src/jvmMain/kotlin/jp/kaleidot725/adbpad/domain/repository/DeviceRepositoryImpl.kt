package jp.kaleidot725.adbpad.domain.repository

import com.malinskiy.adam.AndroidDebugBridgeClientFactory
import com.malinskiy.adam.request.device.ListDevicesRequest
import jp.kaleidot725.adbpad.data.local.DeviceFileCreator
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.model.device.DeviceState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.withContext

class DeviceRepositoryImpl : DeviceRepository {
    private val adbClient = AndroidDebugBridgeClientFactory().build()

    private var lastSelectedDevice: Device? = null
    private val selectedDevice: MutableSharedFlow<Device?> = MutableSharedFlow(replay = 1)

    override suspend fun selectDevice(device: Device?): Boolean {
        lastSelectedDevice = device
        selectedDevice.emit(device)
        return true
    }

    override suspend fun saveDevices(devices: List<Device>): Boolean {
        val result =
            withContext(Dispatchers.IO) {
                val oldSetting = DeviceFileCreator.load()
                val oldSettingDevices = oldSetting.values.associate { it.serial to it.name }

                val newSettingDevices = oldSettingDevices.toMutableMap()
                devices.forEach { device ->
                    newSettingDevices[device.serial] = device.name
                }
                val newSetting =
                    DeviceFileCreator.DevicesSetting(
                        values =
                            newSettingDevices.map {
                                DeviceFileCreator.DeviceSetting(
                                    it.key,
                                    it.value,
                                )
                            },
                    )

                lastSelectedDevice = lastSelectedDevice?.let { it.copy(name = newSettingDevices[it.serial] ?: "") }
                selectedDevice.emit(lastSelectedDevice)

                DeviceFileCreator.save(newSetting)
            }

        return result
    }

    override fun getSelectedDeviceFlow(): Flow<Device?> = selectedDevice.asSharedFlow()

    override suspend fun updateDevices(): List<Device> {
        val deviceSetting = DeviceFileCreator.load()
        val settingDevices = deviceSetting.values.associate { it.serial to it.name }
        val devices = adbClient.execute(request = ListDevicesRequest()).convert(settingDevices)
        if (devices.any { it.serial == lastSelectedDevice?.serial }.not()) {
            selectDevice(devices.firstOrNull())
        }
        return devices
    }

    private fun List<com.malinskiy.adam.request.device.Device>.convert(settingDevices: Map<String, String>): List<Device> =
        map {
            Device(it.serial, settingDevices[it.serial] ?: "", it.state.convert())
        }

    private fun com.malinskiy.adam.request.device.DeviceState.convert(): DeviceState =
        when (this) {
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
