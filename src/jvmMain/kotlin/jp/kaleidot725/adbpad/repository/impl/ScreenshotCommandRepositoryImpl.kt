package jp.kaleidot725.adbpad.repository.impl

import com.malinskiy.adam.AndroidDebugBridgeClientFactory
import com.malinskiy.adam.request.framebuffer.RawImageScreenCaptureAdapter
import com.malinskiy.adam.request.framebuffer.ScreenCaptureRequest
import jp.kaleidot725.adbpad.domain.model.Device
import jp.kaleidot725.adbpad.domain.model.OSContext
import jp.kaleidot725.adbpad.domain.model.ScreenshotPreview
import jp.kaleidot725.adbpad.domain.model.command.NormalCommand
import jp.kaleidot725.adbpad.domain.model.command.ScreenshotCommand
import jp.kaleidot725.adbpad.domain.repository.ScreenshotCommandRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.io.File
import javax.imageio.ImageIO

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

    override fun getPreview(): ScreenshotPreview {
        val files = buildList {
            val fileA = File(getPathA())
            if (fileA.exists()) add(fileA)

            val fileB = File(getPathB())
            if (fileB.exists()) add(fileB)
        }

        return ScreenshotPreview(files)
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

            deleteAll()

            val result = when (command) {
                is ScreenshotCommand.Both -> sendBothCommand(device)
                is ScreenshotCommand.Light -> sendLightCommand(device)
                is ScreenshotCommand.Dark -> sendDarkCommand(device)
                is ScreenshotCommand.Current -> sendCurrentCommand(device)
                else -> false
            }

            delay(300)

            runningCommands.remove(command)
            if (result) onComplete() else onFailed()
        }
    }

    private suspend fun sendBothCommand(device: Device): Boolean {
        if (!sendCommand(device, NormalCommand.DarkThemeOn())) {
            return false
        }

        if (!capture(device, getPathA())) {
            return false
        }

        if (!sendCommand(device, NormalCommand.DarkThemeOff())) {
            return false
        }

        if (!capture(device, getPathB())) {
            return false
        }

        return true
    }

    private suspend fun sendDarkCommand(device: Device): Boolean {
        if (!sendCommand(device, NormalCommand.DarkThemeOn())) {
            return false
        }

        if (!capture(device, getPathA())) {
            return false
        }

        return true
    }

    private suspend fun sendLightCommand(device: Device): Boolean {
        if (!sendCommand(device, NormalCommand.DarkThemeOff())) {
            return false
        }

        if (!capture(device, getPathA())) {
            return false
        }

        return true
    }

    private suspend fun sendCurrentCommand(device: Device): Boolean {
        if (!capture(device, getPathA())) {
            return false
        }

        return true
    }

    private suspend fun capture(device: Device, path: String, delay: Long = DEFAULT_DELAY): Boolean {
        delay(delay)
        val adb = AndroidDebugBridgeClientFactory().build()
        val adapter = RawImageScreenCaptureAdapter()
        val image = adb.execute(request = ScreenCaptureRequest(adapter), serial = device.serial).toBufferedImage()
        return ImageIO.write(image, EXTENSION_NAME, File(path))
    }

    private suspend fun sendCommand(device: Device, command: NormalCommand): Boolean {
        return withContext(Dispatchers.IO) {
            command.requests.forEach { request ->
                val result = adbClient.execute(request, device.serial)
                if (result.exitCode != 0) {
                    return@withContext false
                }
            }
            return@withContext true
        }
    }

    private fun deleteAll() {
        val files = listOf(File(getPathA()), File(getPathB()))
        files.forEach { it.delete() }
    }

    companion object {
        private const val FILE_NAME_A = "screenshotA.png"
        private const val FILE_NAME_B = "screenshotB.png"
        private const val EXTENSION_NAME = "png"
        private const val DEFAULT_DELAY = 500L

        private fun getPathA(): String {
            val osContext = OSContext.resolveOSContext()
            return osContext.directory + FILE_NAME_A
        }

        private fun getPathB(): String {
            val osContext = OSContext.resolveOSContext()
            return osContext.directory + FILE_NAME_B
        }
    }
}
