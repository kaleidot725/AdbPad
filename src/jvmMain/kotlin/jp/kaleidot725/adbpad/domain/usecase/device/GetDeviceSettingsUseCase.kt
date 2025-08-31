package jp.kaleidot725.adbpad.domain.usecase.device

import jp.kaleidot725.adbpad.domain.model.device.DeviceSettings
import jp.kaleidot725.adbpad.domain.repository.DeviceSettingsRepository

class GetDeviceSettingsUseCase(
    private val deviceSettingsRepository: DeviceSettingsRepository,
) {
    suspend operator fun invoke(deviceId: String): DeviceSettings {
        return deviceSettingsRepository.getDeviceSettings(deviceId)
    }
}