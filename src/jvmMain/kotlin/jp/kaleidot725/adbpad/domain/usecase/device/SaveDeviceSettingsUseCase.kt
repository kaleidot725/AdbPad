package jp.kaleidot725.adbpad.domain.usecase.device

import jp.kaleidot725.adbpad.domain.model.device.DeviceSettings
import jp.kaleidot725.adbpad.domain.repository.DeviceSettingsRepository

class SaveDeviceSettingsUseCase(
    private val deviceSettingsRepository: DeviceSettingsRepository,
) {
    suspend operator fun invoke(settings: DeviceSettings): Boolean {
        return deviceSettingsRepository.saveDeviceSettings(settings)
    }
}