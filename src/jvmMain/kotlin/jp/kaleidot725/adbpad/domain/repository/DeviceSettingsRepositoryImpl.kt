package jp.kaleidot725.adbpad.domain.repository

import jp.kaleidot725.adbpad.data.local.DeviceSettingsFileCreator
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.model.device.DeviceSettings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeviceSettingsRepositoryImpl : DeviceSettingsRepository {
    override suspend fun getDeviceSettings(device: Device): DeviceSettings {
        return withContext(Dispatchers.IO) {
            DeviceSettingsFileCreator.load(device.serial)
        }
    }

    override suspend fun saveDeviceSettings(
        device: Device,
        settings: DeviceSettings,
    ): Boolean {
        return withContext(Dispatchers.IO) {
            DeviceSettingsFileCreator.save(settings)
        }
    }

    override suspend fun deleteDeviceSettings(device: Device): Boolean {
        return withContext(Dispatchers.IO) {
            DeviceSettingsFileCreator.delete(device.serial)
        }
    }
}
