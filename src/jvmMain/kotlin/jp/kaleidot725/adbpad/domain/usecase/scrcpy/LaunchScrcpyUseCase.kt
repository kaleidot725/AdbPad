package jp.kaleidot725.adbpad.domain.usecase.scrcpy

import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.repository.ScrcpyProcessRepository
import jp.kaleidot725.adbpad.domain.repository.SettingRepository
import jp.kaleidot725.scrcpykt.ScrcpyClient
import jp.kaleidot725.scrcpykt.ScrcpyResult
import jp.kaleidot725.scrcpykt.builder.ScrcpyCommandBuilder
import java.io.File

class LaunchScrcpyUseCase(
    private val settingRepository: SettingRepository,
    private val scrcpyProcessRepository: ScrcpyProcessRepository,
) {
    suspend operator fun invoke(device: Device): Boolean {
        // Terminate existing process for this device if running
        scrcpyProcessRepository.getProcess(device.serial)?.terminate()

        val scrcpySettings = settingRepository.getScrcpySettings()

        // Check if custom binary path is provided and exists
        if (scrcpySettings.binaryPath.isNotEmpty()) {
            val binaryFile = File(scrcpySettings.binaryPath)
            if (!binaryFile.exists()) {
                throw IllegalArgumentException("Scrcpy binary not found at path: ${scrcpySettings.binaryPath}")
            }
        }

        // Create ScrcpyCommand with or without custom binary path
        val command =
            if (scrcpySettings.binaryPath.isNotEmpty()) {
                ScrcpyCommandBuilder(scrcpySettings.binaryPath)
            } else {
                ScrcpyCommandBuilder()
            }.connection {
                serial(device.serial)
            }.display {
                windowTitle("${device.name} - ${device.serial}")
            }.build()

        val client = ScrcpyClient.create()
        val result = client.execute(command)

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
