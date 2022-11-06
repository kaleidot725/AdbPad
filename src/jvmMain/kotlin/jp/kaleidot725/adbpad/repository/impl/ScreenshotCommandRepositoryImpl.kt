package jp.kaleidot725.adbpad.repository.impl

import com.malinskiy.adam.AndroidDebugBridgeClientFactory
import jp.kaleidot725.adbpad.domain.model.Device
import jp.kaleidot725.adbpad.domain.model.command.ScreenshotCommand
import jp.kaleidot725.adbpad.domain.repository.ScreenshotCommandRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class ScreenshotCommandRepositoryImpl : ScreenshotCommandRepository {
    private val runningCommands: MutableSet<ScreenshotCommand> = mutableSetOf()
    private val adbClient = AndroidDebugBridgeClientFactory().build()

    override fun getCommands(): List<ScreenshotCommand> {
        return listOf(
            ScreenshotCommand.Both(runningCommands.any { it is ScreenshotCommand.Both }),
            ScreenshotCommand.Current(runningCommands.any { it is ScreenshotCommand.Current }),
            ScreenshotCommand.Light(runningCommands.any { it is ScreenshotCommand.Light }),
            ScreenshotCommand.Dark(runningCommands.any { it is ScreenshotCommand.Dark })
        )
    }

    override suspend fun sendCommand(
        device: Device,
        command: ScreenshotCommand,
        onStart: suspend () -> Unit,
        onComplete: suspend () -> Unit,
        onFailed: suspend () -> Unit
    ) {
        withContext(Dispatchers.IO) {
            runningCommands.add(command)
            onStart()

            val result = when (command) {
                is ScreenshotCommand.Both -> sendBothCommand()
                is ScreenshotCommand.Light -> sendLightCommand()
                is ScreenshotCommand.Dark -> sendDarkCommand()
                is ScreenshotCommand.Current -> sendCurrentCommand()
                else -> false
            }

            delay(300)

            runningCommands.remove(command)
            if (result) onComplete() else onFailed()
        }
    }

    private fun sendBothCommand(): Boolean {
        return false
    }

    private fun sendDarkCommand(): Boolean {
        return false
    }

    private fun sendLightCommand(): Boolean {
        return false
    }

    private fun sendCurrentCommand(): Boolean {
        return false
    }

}
