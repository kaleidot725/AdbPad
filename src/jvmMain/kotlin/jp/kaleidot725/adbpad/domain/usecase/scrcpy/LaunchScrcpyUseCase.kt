package jp.kaleidot725.adbpad.domain.usecase.scrcpy

import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.repository.DeviceSettingsRepository
import jp.kaleidot725.adbpad.domain.repository.ScrcpyProcessRepository
import jp.kaleidot725.adbpad.domain.repository.SettingRepository
import jp.kaleidot725.scrcpykt.ScrcpyClient
import jp.kaleidot725.scrcpykt.ScrcpyResult

class LaunchScrcpyUseCase(
    private val settingRepository: SettingRepository,
    private val scrcpyProcessRepository: ScrcpyProcessRepository,
    private val deviceSettingsRepository: DeviceSettingsRepository,
) {
    suspend operator fun invoke(device: Device): Boolean {
        // Terminate existing process for this device if running
        scrcpyProcessRepository.getProcess(device.serial)?.terminate()

        val scrcpySettings = settingRepository.getScrcpySettings()
        val scrcpyPath = scrcpySettings.binaryPath

        val adbSettings = settingRepository.getSdkPath()
        val adbPath = adbSettings.adbDirectory

        // Get device-specific settings
        val deviceSettings = deviceSettingsRepository.getDeviceSettings(device)
        val scrcpyOptions = deviceSettings.scrcpyOptions

        // Use custom name if available, otherwise use device name
        val displayName = deviceSettings.customName ?: device.name

        val client = ScrcpyClient.create(binaryPath = scrcpyPath, adbPath = adbPath)
        val result =
            client.mirror {
                // Apply video settings from device configuration
                video {
                    scrcpyOptions.maxSize?.let { maxSize(it) }
                    scrcpyOptions.videoBitRate?.let { bitRate(it) }
                    scrcpyOptions.maxFps?.let { maxFps(it) }
                    scrcpyOptions.videoCodec?.let { codec(it) }
                    scrcpyOptions.videoSource?.let { source(it) }
                    if (scrcpyOptions.noVideo) disableVideo()
                }

                // Apply audio settings
                audio {
                    if (scrcpyOptions.noAudio) {
                        disableAudio()
                    } else {
                        scrcpyOptions.audioBitRate?.let { bitRate(it) }
                        scrcpyOptions.audioCodec?.let { codec(it) }
                        scrcpyOptions.audioSource?.let { source(it) }
                        scrcpyOptions.audioBuffer?.let { buffer(it) }
                    }
                }

                // Apply display settings
                display {
                    val title = scrcpyOptions.windowTitle ?: "$displayName - ${device.serial}"
                    windowTitle(title)
                    scrcpyOptions.displayId?.let { displayId(it) }
                    scrcpyOptions.windowX?.let { x ->
                        scrcpyOptions.windowY?.let { y -> windowPosition(x, y) }
                    }
                    scrcpyOptions.windowWidth?.let { width ->
                        scrcpyOptions.windowHeight?.let { height -> windowSize(width, height) }
                    }
                    if (scrcpyOptions.alwaysOnTop) alwaysOnTop()
                    if (scrcpyOptions.fullscreen) fullscreen()
                }

                // Apply control settings
                control {
                    if (scrcpyOptions.stayAwake) stayAwake()
                    if (scrcpyOptions.turnScreenOff) turnScreenOff()
                    if (scrcpyOptions.powerOffOnClose) powerOffOnClose()
                    if (scrcpyOptions.showTouches) showTouches()
                    if (scrcpyOptions.disableScreensaver) disableScreensaver()
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
