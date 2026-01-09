package jp.kaleidot725.adbpad.domain.repository

import com.malinskiy.adam.AndroidDebugBridgeClientFactory
import jp.kaleidot725.adbpad.domain.model.command.NormalCommand
import jp.kaleidot725.adbpad.domain.model.device.Device
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class NormalCommandRepositoryImpl : NormalCommandRepository {
    private val runningCommands: MutableSet<NormalCommand> = mutableSetOf()
    private val commandResults: MutableMap<String, String> = mutableMapOf()
    private val adbClient = AndroidDebugBridgeClientFactory().build()

    override fun getCommands(): List<NormalCommand> =
        listOf(
            NormalCommand.PointerLocationOn(runningCommands.any { it is NormalCommand.PointerLocationOn }, commandResults["PointerLocationOn"]),
            NormalCommand.PointerLocationOff(runningCommands.any { it is NormalCommand.PointerLocationOff }, commandResults["PointerLocationOff"]),
            NormalCommand.LayoutBorderOn(runningCommands.any { it is NormalCommand.LayoutBorderOn }, commandResults["LayoutBorderOn"]),
            NormalCommand.LayoutBorderOff(runningCommands.any { it is NormalCommand.LayoutBorderOff }, commandResults["LayoutBorderOff"]),
            NormalCommand.TapEffectOn(runningCommands.any { it is NormalCommand.TapEffectOn }, commandResults["TapEffectOn"]),
            NormalCommand.TapEffectOff(runningCommands.any { it is NormalCommand.TapEffectOff }, commandResults["TapEffectOff"]),
            NormalCommand.SleepModeOff(runningCommands.any { it is NormalCommand.SleepModeOff }, commandResults["SleepModeOff"]),
            NormalCommand.SleepModeOn(runningCommands.any { it is NormalCommand.SleepModeOn }, commandResults["SleepModeOn"]),
            NormalCommand.DarkThemeOn(runningCommands.any { it is NormalCommand.DarkThemeOn }, commandResults["DarkThemeOn"]),
            NormalCommand.DarkThemeOff(runningCommands.any { it is NormalCommand.DarkThemeOff }, commandResults["DarkThemeOff"]),
            NormalCommand.WifiOn(runningCommands.any { it is NormalCommand.WifiOn }, commandResults["WifiOn"]),
            NormalCommand.WifiOff(runningCommands.any { it is NormalCommand.WifiOff }, commandResults["WifiOff"]),
            NormalCommand.DataOn(runningCommands.any { it is NormalCommand.DataOn }, commandResults["DataOn"]),
            NormalCommand.DataOff(runningCommands.any { it is NormalCommand.DataOff }, commandResults["DataOff"]),
            NormalCommand.WifiAndDataOn(runningCommands.any { it is NormalCommand.WifiAndDataOn }, commandResults["WifiAndDataOn"]),
            NormalCommand.WifiAndDataOff(runningCommands.any { it is NormalCommand.WifiAndDataOff }, commandResults["WifiAndDataOff"]),
            NormalCommand.ScreenPinningOff(runningCommands.any { it is NormalCommand.ScreenPinningOff }, commandResults["ScreenPinningOff"]),
            NormalCommand.EnableGestureNavigation(runningCommands.any { it is NormalCommand.EnableGestureNavigation }, commandResults["EnableGestureNavigation"]),
            NormalCommand.EnableTwoButtonNavigation(runningCommands.any { it is NormalCommand.EnableTwoButtonNavigation }, commandResults["EnableTwoButtonNavigation"]),
            NormalCommand.EnableThreeButtonNavigation(runningCommands.any { it is NormalCommand.EnableThreeButtonNavigation }, commandResults["EnableThreeButtonNavigation"]),
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
                onStart()

                delay(300)

                val outputs = mutableListOf<String>()
                command.requests.forEach { request ->
                    val result = adbClient.execute(request, device.serial)

                    // 標準出力を取得
                    val output = result.output.trim()
                    if (output.isNotEmpty()) {
                        outputs.add(output)
                    }

                    if (result.exitCode != 0) {
                        val errorMessage = if (output.isNotEmpty()) {
                            "Error (exit code ${result.exitCode}):\n$output"
                        } else {
                            "Error: Command failed with exit code ${result.exitCode}"
                        }
                        commandResults[command::class.simpleName ?: ""] = errorMessage
                        runningCommands.remove(command)
                        onFailed()
                        return@withContext
                    }
                }

                // 成功時の結果を保存
                val commandKey = command::class.simpleName ?: ""
                commandResults[commandKey] = if (outputs.isEmpty()) {
                    "Success"
                } else {
                    outputs.joinToString("\n")
                }

                runningCommands.remove(command)
                onComplete()
            } catch (e: Exception) {
                val commandKey = command::class.simpleName ?: ""
                commandResults[commandKey] = "Exception: ${e.message}"
                runningCommands.remove(command)
                onFailed()
            }
        }
    }

    override fun clear() {
        runningCommands.clear()
    }
}
