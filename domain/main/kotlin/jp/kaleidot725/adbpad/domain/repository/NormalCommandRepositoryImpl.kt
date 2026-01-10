package jp.kaleidot725.adbpad.domain.repository

import com.malinskiy.adam.AndroidDebugBridgeClientFactory
import jp.kaleidot725.adbpad.domain.model.command.CommandExecutionHistory
import jp.kaleidot725.adbpad.domain.model.command.NormalCommand
import jp.kaleidot725.adbpad.domain.model.device.Device
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class NormalCommandRepositoryImpl(
    private val outputRepository: NormalCommandOutputRepository,
) : NormalCommandRepository {
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
                        // エラー時の実行履歴を追加
                        val history =
                            CommandExecutionHistory(
                                commandStrings = command.commandStrings,
                                output = output.ifEmpty { "Error: Command failed with exit code ${result.exitCode}" },
                            )
                        outputRepository.addExecutionHistory(history)

                        runningCommands.remove(command)
                        onFailed()
                        return@withContext
                    }
                }

                // 成功時の実行履歴を追加
                val history =
                    CommandExecutionHistory(
                        commandStrings = command.commandStrings,
                        output = outputs.joinToString("\n").ifEmpty { "Success" },
                    )
                outputRepository.addExecutionHistory(history)

                runningCommands.remove(command)
                onComplete()
            } catch (e: Exception) {
                // 例外時の実行履歴を追加
                val history =
                    CommandExecutionHistory(
                        commandStrings = command.commandStrings,
                        output = "Exception: ${e.message}",
                    )
                outputRepository.addExecutionHistory(history)

                runningCommands.remove(command)
                onFailed()
            }
        }
    }

    override fun clear() {
        runningCommands.clear()
    }
}
