package jp.kaleidot725.adbpad.domain.repository

import jp.kaleidot725.adbpad.data.local.DeviceSettingsFileCreator
import jp.kaleidot725.adbpad.domain.model.device.DeviceSettings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeviceSettingsRepositoryImpl : DeviceSettingsRepository {
    override suspend fun getDeviceSettings(deviceId: String): DeviceSettings {
        return withContext(Dispatchers.IO) {
            DeviceSettingsFileCreator.load(deviceId)
        }
    }

    override suspend fun saveDeviceSettings(settings: DeviceSettings): Boolean {
        return withContext(Dispatchers.IO) {
            DeviceSettingsFileCreator.save(settings)
        }
    }

    override suspend fun deleteDeviceSettings(deviceId: String): Boolean {
        return withContext(Dispatchers.IO) {
            DeviceSettingsFileCreator.delete(deviceId)
        }
    }
}