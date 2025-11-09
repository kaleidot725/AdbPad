package jp.kaleidot725.adbpad.domain.usecase.scrcpy

import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.model.scrcpy.ScrcpyNewDisplayProfile
import jp.kaleidot725.adbpad.domain.repository.ScrcpyNewDisplayProcessRepository
import jp.kaleidot725.adbpad.domain.repository.SettingRepository
import jp.kaleidot725.scrcpykt.ScrcpyClient
import jp.kaleidot725.scrcpykt.ScrcpyResult

class LaunchScrcpyNewDisplayUseCase(
    private val settingRepository: SettingRepository,
    private val scrcpyNewDisplayProcessRepository: ScrcpyNewDisplayProcessRepository,
) {
    suspend operator fun invoke(
        device: Device,
        profile: ScrcpyNewDisplayProfile,
    ): Boolean {
        val scrcpySettings = settingRepository.getScrcpySettings()
        val adbSettings = settingRepository.getSdkPath()

        if (scrcpySettings.binaryPath.isBlank()) {
            return false
        }

        if (adbSettings.adbDirectory.isBlank()) {
            return false
        }

        scrcpyNewDisplayProcessRepository.getProcess(device.serial, profile.id)?.let { existingProcess ->
            try {
                existingProcess.terminate()
            } finally {
                scrcpyNewDisplayProcessRepository.removeProcess(device.serial, profile.id)
            }
        }

        val client = ScrcpyClient.create(binaryPath = scrcpySettings.binaryPath, adbPath = adbSettings.adbDirectory)
        val title = "${profile.displayName} - Virtual Display"

        val result =
            client.mirror {
                connection {
                    serial(device.serial)
                }
                display {
                    windowTitle(title)
                    newDisplay(profile.width, profile.height)
                }
            }

        return when (result) {
            is ScrcpyResult.Success -> {
                scrcpyNewDisplayProcessRepository.storeProcess(device.serial, profile.id, result.process)
                true
            }

            is ScrcpyResult.Error -> false
        }
    }
}
