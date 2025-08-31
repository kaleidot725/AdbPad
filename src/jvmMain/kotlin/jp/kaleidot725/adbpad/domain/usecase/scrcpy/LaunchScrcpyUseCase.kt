package jp.kaleidot725.adbpad.domain.usecase.scrcpy

import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.repository.ScrcpyProcessRepository
import jp.kaleidot725.adbpad.domain.repository.SettingRepository
import jp.kaleidot725.adbpad.domain.usecase.device.GetDeviceSettingsUseCase
import jp.kaleidot725.scrcpykt.ScrcpyClient
import jp.kaleidot725.scrcpykt.ScrcpyResult

class LaunchScrcpyUseCase(
    private val settingRepository: SettingRepository,
    private val scrcpyProcessRepository: ScrcpyProcessRepository,
    private val getDeviceSettingsUseCase: GetDeviceSettingsUseCase,
) {
    suspend operator fun invoke(device: Device): Boolean {
        // Terminate existing process for this device if running
        scrcpyProcessRepository.getProcess(device.serial)?.terminate()

        val scrcpySettings = settingRepository.getScrcpySettings()
        val scrcpyPath = scrcpySettings.binaryPath

        val adbSettings = settingRepository.getSdkPath()
        val adbPath = adbSettings.adbDirectory

        // Get device-specific settings
        val deviceSettings = getDeviceSettingsUseCase(device.serial)
        
        // Use custom name if available, otherwise use device name
        val displayName = deviceSettings.customName ?: device.name

        val client = ScrcpyClient.create(binaryPath = scrcpyPath, adbPath = adbPath)
        val result =
            client.mirror {
                display {
                    windowTitle("$displayName - ${device.serial}")
                }
            }

        // Handle result if needed
        return when (result) {
            is ScrcpyResult.Success -> {
                scrcpyProcessRepository.storeProcess(device.serial, result.process)
                true
            }

            is ScrcpyResult.Error -> {
                false
            }
        }
    }
}
