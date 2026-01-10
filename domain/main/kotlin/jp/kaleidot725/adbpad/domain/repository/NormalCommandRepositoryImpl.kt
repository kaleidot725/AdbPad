package jp.kaleidot725.adbpad.domain.repository

import com.malinskiy.adam.AndroidDebugBridgeClientFactory
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
        )

    override suspend fun sendCommand(
        device: Device,
        command: NormalCommand,
        onStart: suspend () -> Unit,
        onComplete: suspend (command: String, output: String) -> Unit,
        onFailed: suspend (command: String, output: String) -> Unit,
    ) {
        withContext(Dispatchers.IO) {
            try {
                runningCommands.add(command)
                onStart()

                delay(300)

                val outputs = mutableListOf<String>()
                val formattedCommand =
                    command.commandStrings.joinToString("\n") { "$ adb shell $it" }

                command.requests.forEach { request ->
                    val result = adbClient.execute(request, device.serial)

                    // 標準出力を取得
                    val output = result.output.trim()
                    if (output.isNotEmpty()) {
                        outputs.add(output)
                    }

                    if (result.exitCode != 0) {
                        runningCommands.remove(command)
                        onFailed(
                            formattedCommand,
                            output.ifEmpty { "Error: Command failed with exit code ${result.exitCode}" },
                        )
                        return@withContext
                    }
                }

                runningCommands.remove(command)
                onComplete(
                    formattedCommand,
                    outputs.joinToString("\n").ifEmpty { "Success" },
                )
            } catch (e: Exception) {
                runningCommands.remove(command)
                onFailed(
                    command.commandStrings.joinToString("\n") { "$ adb shell $it" },
                    "Exception: ${e.message}",
                )
            }
        }
    }

    override fun clear() {
        runningCommands.clear()
    }
}
