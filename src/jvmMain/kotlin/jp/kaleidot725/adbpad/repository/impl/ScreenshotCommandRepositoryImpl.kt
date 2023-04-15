package jp.kaleidot725.adbpad.repository.impl

import com.malinskiy.adam.AndroidDebugBridgeClientFactory
import com.malinskiy.adam.request.framebuffer.RawImageScreenCaptureAdapter
import com.malinskiy.adam.request.framebuffer.ScreenCaptureRequest
import jp.kaleidot725.adbpad.domain.model.command.NormalCommand
import jp.kaleidot725.adbpad.domain.model.command.ScreenshotCommand
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.model.os.OSContext
import jp.kaleidot725.adbpad.domain.model.screenshot.ScreenshotPreview
import jp.kaleidot725.adbpad.domain.repository.ScreenshotCommandRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO
import kotlin.math.max


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
        val fileResult = getFileResult()
        return if (fileResult.exists()) {
            ScreenshotPreview(fileResult)
        } else {
            ScreenshotPreview(null)
        }
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

        if (!capture(device, getFileA())) {
            return false
        }

        if (!sendCommand(device, NormalCommand.DarkThemeOff())) {
            return false
        }

        if (!capture(device, getFileB())) {
            return false
        }

        return concat(getFileA(), getFileB(), getFileResult())
    }

    private suspend fun sendDarkCommand(device: Device): Boolean {
        if (!sendCommand(device, NormalCommand.DarkThemeOn())) {
            return false
        }

        return capture(device, getFileResult())
    }

    private suspend fun sendLightCommand(device: Device): Boolean {
        if (!sendCommand(device, NormalCommand.DarkThemeOff())) {
            return false
        }

        return capture(device, getFileResult())
    }

    private suspend fun sendCurrentCommand(device: Device): Boolean {
        return capture(device, getFileResult())
    }

    private suspend fun capture(device: Device, file: File, delay: Long = DEFAULT_DELAY): Boolean {
        delay(delay)
        val adb = AndroidDebugBridgeClientFactory().build()
        val adapter = RawImageScreenCaptureAdapter()
        val image = adb.execute(request = ScreenCaptureRequest(adapter), serial = device.serial).toBufferedImage()
        return ImageIO.write(image, EXTENSION_NAME, file)
    }

    private fun concat(fileA: File, fileB: File, fileResult: File): Boolean {
        return try {
            val imageA = ImageIO.read(fileA)
            val imageB = ImageIO.read(fileB)
            val totalWidth = imageA.width + imageB.width
            val maxHeight = max(imageA.height, imageB.height)
            val imageC = BufferedImage(totalWidth, maxHeight, BufferedImage.TYPE_INT_ARGB)

            imageC.graphics.drawImage(imageA, 0, 0, null)
            imageC.graphics.drawImage(imageB, imageA.width, 0, null)
            ImageIO.write(imageC, "PNG", fileResult)

            true
        } catch (e: IOException) {
            false
        }
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
        val files = listOf(getFileA(), getFileB(), getFileResult())
        files.forEach { it.delete() }
    }

    companion object {
        private const val FILE_NAME_A = "screenshotA.png"
        private const val FILE_NAME_B = "screenshotB.png"
        private const val FILE_NAME_RESULT = "Screenshot.png"
        private const val EXTENSION_NAME = "png"
        private const val DEFAULT_DELAY = 500L

        private fun getFileA(): File {
            val osContext = OSContext.resolveOSContext()
            return File(osContext.directory + FILE_NAME_A)
        }

        private fun getFileB(): File {
            val osContext = OSContext.resolveOSContext()
            return File(osContext.directory + FILE_NAME_B)
        }

        private fun getFileResult(): File {
            val osContext = OSContext.resolveOSContext()
            return File(osContext.directory + FILE_NAME_RESULT)
        }
    }
}
