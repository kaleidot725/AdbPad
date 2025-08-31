package jp.kaleidot725.adbpad.domain.repository

import com.malinskiy.adam.AndroidDebugBridgeClientFactory
import com.malinskiy.adam.request.device.ListDevicesRequest
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.model.device.DeviceState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.withContext

class DeviceRepositoryImpl(
    private val deviceSettingsRepository: DeviceSettingsRepository
) : DeviceRepository {
    private val adbClient = AndroidDebugBridgeClientFactory().build()

    private var lastSelectedDevice: Device? = null
    private val selectedDevice: MutableSharedFlow<Device?> = MutableSharedFlow(replay = 1)

    override suspend fun selectDevice(device: Device?): Boolean {
        lastSelectedDevice = device
        selectedDevice.emit(device)
        return true
    }

    override suspend fun saveDevices(devices: List<Device>): Boolean {
        return withContext(Dispatchers.IO) {
            // For each device, get existing settings or create new ones
            devices.forEach { device ->
                val existingSettings = deviceSettingsRepository.getDeviceSettings(device.serial)
                val updatedSettings = existingSettings.copy(customName = device.name)
                deviceSettingsRepository.saveDeviceSettings(updatedSettings)
            }
            true
        }
    }

    override fun getSelectedDeviceFlow(): Flow<Device?> = selectedDevice.asSharedFlow()

    override suspend fun updateDevices(): List<Device> {
        val rawDevices = adbClient.execute(request = ListDevicesRequest())
        val devices = rawDevices.map { rawDevice ->
            val deviceSettings = deviceSettingsRepository.getDeviceSettings(rawDevice.serial)
            Device(
                rawDevice.serial, 
                deviceSettings.customName ?: "",
                rawDevice.state.convert()
            )
        }
        if (devices.any { it.serial == lastSelectedDevice?.serial }.not()) {
            selectDevice(devices.firstOrNull())
        }
        return devices
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
