package jp.kaleidot725.adbpad.domain.repository

import com.malinskiy.adam.AndroidDebugBridgeClientFactory
import com.malinskiy.adam.request.shell.v1.ShellCommandRequest
import jp.kaleidot725.adbpad.domain.model.command.NormalCommand
import jp.kaleidot725.adbpad.domain.model.device.Device
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class NormalCommandRepositoryImpl : NormalCommandRepository {
    private val runningCommands: MutableSet<NormalCommand> = mutableSetOf()
    private val adbClient = AndroidDebugBridgeClientFactory().build()

    override fun getCommands(): List<NormalCommand> =
        listOf(
            NormalCommand.PointerLocationOn(runningCommands.any { it is NormalCommand.PointerLocationOn }),
            NormalCommand.PointerLocationOff(runningCommands.any { it is NormalCommand.PointerLocationOff }),
            NormalCommand.LayoutBorderOn(runningCommands.any { it is NormalCommand.LayoutBorderOn }),
            NormalCommand.LayoutBorderOff(runningCommands.any { it is NormalCommand.LayoutBorderOff }),
            NormalCommand.TapEffectOn(runningCommands.any { it is NormalCommand.TapEffectOn }),
            NormalCommand.TapEffectOff(runningCommands.any { it is NormalCommand.TapEffectOff }),
            NormalCommand.SleepModeOff(runningCommands.any { it is NormalCommand.SleepModeOff }),
            NormalCommand.SleepModeOn(runningCommands.any { it is NormalCommand.SleepModeOn }),
            NormalCommand.DarkThemeOn(runningCommands.any { it is NormalCommand.DarkThemeOn }),
            NormalCommand.DarkThemeOff(runningCommands.any { it is NormalCommand.DarkThemeOff }),
            NormalCommand.WifiOn(runningCommands.any { it is NormalCommand.WifiOn }),
            NormalCommand.WifiOff(runningCommands.any { it is NormalCommand.WifiOff }),
            NormalCommand.DataOn(runningCommands.any { it is NormalCommand.DataOn }),
            NormalCommand.DataOff(runningCommands.any { it is NormalCommand.DataOff }),
            NormalCommand.WifiAndDataOn(runningCommands.any { it is NormalCommand.WifiAndDataOn }),
            NormalCommand.WifiAndDataOff(runningCommands.any { it is NormalCommand.WifiAndDataOff }),
            NormalCommand.ScreenPinningOff(runningCommands.any { it is NormalCommand.ScreenPinningOff }),
            NormalCommand.EnableGestureNavigation(runningCommands.any { it is NormalCommand.EnableGestureNavigation }),
            NormalCommand.EnableTwoButtonNavigation(runningCommands.any { it is NormalCommand.EnableTwoButtonNavigation }),
            NormalCommand.EnableThreeButtonNavigation(runningCommands.any { it is NormalCommand.EnableThreeButtonNavigation }),
            NormalCommand.SetLanguageJapanese(runningCommands.any { it is NormalCommand.SetLanguageJapanese }),
            NormalCommand.SetLanguageEnglish(runningCommands.any { it is NormalCommand.SetLanguageEnglish }),
            NormalCommand.SetLanguageChinese(runningCommands.any { it is NormalCommand.SetLanguageChinese }),
            NormalCommand.SetLanguageKorean(runningCommands.any { it is NormalCommand.SetLanguageKorean }),
            NormalCommand.SetLanguageSpanish(runningCommands.any { it is NormalCommand.SetLanguageSpanish }),
            NormalCommand.SetLanguageFrench(runningCommands.any { it is NormalCommand.SetLanguageFrench }),
            NormalCommand.SetLanguageGerman(runningCommands.any { it is NormalCommand.SetLanguageGerman }),
        )

    override suspend fun sendCommand(
        device: Device,
        command: NormalCommand,
        onStart: suspend () -> Unit,
        onComplete: suspend () -> Unit,
        onFailed: suspend () -> Unit,
    ) {
        withContext(Dispatchers.IO) {
            try {
                runningCommands.add(command)

                // Handle language setting commands with version-specific logic
                when (command) {
                    is NormalCommand.SetLanguageJapanese -> {
                        executeLanguageSettingCommand(device, "ja-JP", onStart, {
                            runningCommands.remove(command)
                            onComplete()
                        }, {
                            runningCommands.remove(command)
                            onFailed()
                        })
                    }
                    is NormalCommand.SetLanguageEnglish -> {
                        executeLanguageSettingCommand(device, "en-US", onStart, {
                            runningCommands.remove(command)
                            onComplete()
                        }, {
                            runningCommands.remove(command)
                            onFailed()
                        })
                    }
                    is NormalCommand.SetLanguageChinese -> {
                        executeLanguageSettingCommand(device, "zh-CN", onStart, {
                            runningCommands.remove(command)
                            onComplete()
                        }, {
                            runningCommands.remove(command)
                            onFailed()
                        })
                    }
                    is NormalCommand.SetLanguageKorean -> {
                        executeLanguageSettingCommand(device, "ko-KR", onStart, {
                            runningCommands.remove(command)
                            onComplete()
                        }, {
                            runningCommands.remove(command)
                            onFailed()
                        })
                    }
                    is NormalCommand.SetLanguageSpanish -> {
                        executeLanguageSettingCommand(device, "es-ES", onStart, {
                            runningCommands.remove(command)
                            onComplete()
                        }, {
                            runningCommands.remove(command)
                            onFailed()
                        })
                    }
                    is NormalCommand.SetLanguageFrench -> {
                        executeLanguageSettingCommand(device, "fr-FR", onStart, {
                            runningCommands.remove(command)
                            onComplete()
                        }, {
                            runningCommands.remove(command)
                            onFailed()
                        })
                    }
                    is NormalCommand.SetLanguageGerman -> {
                        executeLanguageSettingCommand(device, "de-DE", onStart, {
                            runningCommands.remove(command)
                            onComplete()
                        }, {
                            runningCommands.remove(command)
                            onFailed()
                        })
                    }
                    else -> {
                        // Standard command execution for non-language commands
                        onStart()
                        delay(300)

                        command.requests.forEach { request ->
                            val result = adbClient.execute(request, device.serial)
                            if (result.exitCode != 0) {
                                runningCommands.remove(command)
                                onFailed()
                                return@withContext
                            }
                        }

                        runningCommands.remove(command)
                        onComplete()
                    }
                }
            } catch (e: Exception) {
                runningCommands.remove(command)
                onFailed()
            }
        }
    }

    override fun clear() {
        runningCommands.clear()
    }

    /**
     * Get Android SDK API level for the device
     * @param deviceSerial Serial number of the device
     * @return SDK API level as integer, or -1 if failed to retrieve
     */
    private suspend fun getAndroidSdkVersion(deviceSerial: String): Int =
        try {
            val request = ShellCommandRequest("getprop ro.build.version.sdk")
            val result = adbClient.execute(request, deviceSerial)
            if (result.exitCode == 0) {
                result.output.trim().toIntOrNull() ?: -1
            } else {
                -1
            }
        } catch (e: Exception) {
            -1
        }

    /**
     * Generate language setting commands based on Android version
     * @param localeCode Locale code (e.g., "ja-JP", "en-US")
     * @param sdkVersion Android SDK API level
     * @return List of ShellCommandRequest for the appropriate Android version
     */
    private fun getLanguageSettingCommands(
        localeCode: String,
        sdkVersion: Int,
    ): List<ShellCommandRequest> =
        when {
            // Android 13+ (API 33+): Use am set-locales command (preferred method)
            sdkVersion >= 33 -> {
                listOf(
                    // Primary method: Use am set-locales command
                    ShellCommandRequest("am set-locales $localeCode"),
                    // Fallback 1: Traditional setprop method for compatibility
                    ShellCommandRequest("setprop persist.sys.locale $localeCode"),
                    ShellCommandRequest("am broadcast -a android.intent.action.LOCALE_CHANGED"),
                )
            }
            // Android 12 (API 31-32): Try am set-locales first, then traditional method
            sdkVersion in 31..32 -> {
                listOf(
                    // Try modern am set-locales command first
                    ShellCommandRequest("am set-locales $localeCode"),
                    // Fallback to traditional method
                    ShellCommandRequest("setprop persist.sys.locale $localeCode"),
                    ShellCommandRequest("am broadcast -a android.intent.action.LOCALE_CHANGED"),
                )
            }
            // Android 11 and earlier (API 30 and below): Traditional method only
            sdkVersion in 1..30 -> {
                listOf(
                    ShellCommandRequest("setprop persist.sys.locale $localeCode"),
                    ShellCommandRequest("am broadcast -a android.intent.action.LOCALE_CHANGED"),
                )
            }
            // Unknown version: Use modern approach as default
            else -> {
                listOf(
                    ShellCommandRequest("am set-locales $localeCode"),
                    ShellCommandRequest("setprop persist.sys.locale $localeCode"),
                    ShellCommandRequest("am broadcast -a android.intent.action.LOCALE_CHANGED"),
                )
            }
        }

    /**
     * Execute language setting command with version-specific implementation
     * @param device Target device
     * @param localeCode Locale code to set (e.g., "ja-JP", "en-US")
     * @param onStart Callback when command starts
     * @param onComplete Callback when command completes successfully
     * @param onFailed Callback when command fails
     */
    private suspend fun executeLanguageSettingCommand(
        device: Device,
        localeCode: String,
        onStart: suspend () -> Unit,
        onComplete: suspend () -> Unit,
        onFailed: suspend () -> Unit,
    ) {
        withContext(Dispatchers.IO) {
            try {
                onStart()
                delay(300)

                // Get Android SDK version for appropriate command selection
                val sdkVersion = getAndroidSdkVersion(device.serial)

                // Get appropriate commands for the Android version
                val commands = getLanguageSettingCommands(localeCode, sdkVersion)

                var hasSuccessfulCommand = false
                var lastError: String? = null

                // Execute commands sequentially
                for ((index, shellCommand) in commands.withIndex()) {
                    val result = adbClient.execute(shellCommand, device.serial)

                    if (result.exitCode == 0) {
                        // Command succeeded
                        hasSuccessfulCommand = true
                        println("Language setting command #$index succeeded")

                        // For am set-locales command (usually the first one), we can consider it complete if it succeeds
                        if (index == 0 && sdkVersion >= 31) {
                            println("Modern am set-locales command succeeded, skipping fallback methods")
                            break // No need to try fallback methods if modern method works
                        }
                    } else {
                        // Command failed - log but continue to try other methods
                        val errorMessage = "Language setting command #$index failed, exit code: ${result.exitCode}"
                        println(errorMessage)
                        lastError = errorMessage

                        // Continue with the next command as fallback
                        continue
                    }
                }

                if (hasSuccessfulCommand) {
                    onComplete()
                } else {
                    println("All language setting commands failed. Last error: $lastError")
                    onFailed()
                }
            } catch (e: Exception) {
                println("Exception during language setting: ${e.message}")
                onFailed()
            }
        }
    }
}
