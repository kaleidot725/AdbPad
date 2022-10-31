package jp.kaleidot725.adbpad.repository.impl

import com.malinskiy.adam.AndroidDebugBridgeClientFactory
import jp.kaleidot725.adbpad.domain.model.Command
import jp.kaleidot725.adbpad.domain.model.Device
import jp.kaleidot725.adbpad.domain.repository.CommandRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class CommandRepositoryImpl : CommandRepository {
    private val runningCommands: MutableSet<Command> = mutableSetOf()
    private val adbClient = AndroidDebugBridgeClientFactory().build()

    override fun getCommands(): List<Command> {
        return listOf(
            Command.LayoutBorderOn(runningCommands.any { it is Command.LayoutBorderOn }),
            Command.LayoutBorderOff(runningCommands.any { it is Command.LayoutBorderOff }),
            Command.TapEffectOn(runningCommands.any { it is Command.TapEffectOn }),
            Command.TapEffectOff(runningCommands.any { it is Command.TapEffectOff }),
            Command.SleepModeOff(runningCommands.any { it is Command.SleepModeOff }),
            Command.SleepModeOn(runningCommands.any { it is Command.SleepModeOn }),
            Command.DarkThemeOn(runningCommands.any { it is Command.DarkThemeOn }),
            Command.DarkThemeOff(runningCommands.any { it is Command.DarkThemeOff }),
            Command.WifiOn(runningCommands.any { it is Command.WifiOn }),
            Command.WifiOff(runningCommands.any { it is Command.WifiOff }),
            Command.DataOn(runningCommands.any { it is Command.DataOn }),
            Command.DataOff(runningCommands.any { it is Command.DataOff }),
            Command.WifiAndDataOn(runningCommands.any { it is Command.WifiAndDataOn }),
            Command.WifiAndDataOff(runningCommands.any { it is Command.WifiAndDataOff }),
        )
    }

    override suspend fun sendCommand(
        device: Device,
        command: Command,
        onStart: suspend () -> Unit,
        onComplete: suspend () -> Unit,
        onFailed: suspend () -> Unit
    ) {
        withContext(Dispatchers.IO) {
            runningCommands.add(command)
            onStart()

            delay(300)

            command.requests.forEach { request ->
                val result = adbClient.execute(request, device.serial)
                if (result.exitCode != 0) {
                    runningCommands.remove(command)
                    onFailed()
                    return@forEach
                }
            }

            runningCommands.remove(command)
            onComplete()
        }
    }
}
