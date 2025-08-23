package jp.kaleidot725.adbpad.domain.usecase.scrcpy

import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.repository.SettingRepository
import jp.kaleidot725.scrcpykt.ScrcpyClient
import jp.kaleidot725.scrcpykt.ScrcpyResult
import jp.kaleidot725.scrcpykt.builder.ScrcpyCommandBuilder
import java.io.File

class LaunchScrcpyUseCase(
    private val settingRepository: SettingRepository,
) {
    suspend operator fun invoke(device: Device) {
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
        when (result) {
            is ScrcpyResult.Success -> {
                println("Scrcpy started successfully for device: ${device.serial}")
            }
            is ScrcpyResult.Error -> {
                throw RuntimeException("Failed to start Scrcpy: ${result.message}")
            }
        }
    }
}
