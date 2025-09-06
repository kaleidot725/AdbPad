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
            } catch (e: Exception) {
                runningCommands.remove(command)
                onFailed()
            }
        }
    }

    override fun clear() {
        runningCommands.clear()
    }
}
