package jp.kaleidot725.adbpad.domain.repository

import jp.kaleidot725.adbpad.domain.model.device.DeviceSettings

interface DeviceSettingsRepository {
    suspend fun getDeviceSettings(deviceId: String): DeviceSettings
    suspend fun saveDeviceSettings(settings: DeviceSettings): Boolean
    suspend fun deleteDeviceSettings(deviceId: String): Boolean
}