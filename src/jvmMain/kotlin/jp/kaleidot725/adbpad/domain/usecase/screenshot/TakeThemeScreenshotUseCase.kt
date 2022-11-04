package jp.kaleidot725.adbpad.domain.usecase.screenshot

import com.malinskiy.adam.AndroidDebugBridgeClientFactory
import com.malinskiy.adam.request.framebuffer.RawImageScreenCaptureAdapter
import com.malinskiy.adam.request.framebuffer.ScreenCaptureRequest
import jp.kaleidot725.adbpad.domain.model.OSContext
import jp.kaleidot725.adbpad.domain.model.command.Command
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import javax.imageio.ImageIO

class TakeThemeScreenshotUseCase {
    suspend operator fun invoke(serial: String?): Pair<String, String>? {
        return withContext(Dispatchers.IO) {
            val adb = AndroidDebugBridgeClientFactory().build()
            val adapter = RawImageScreenCaptureAdapter()

            Command.DarkThemeOn().requests.forEach {
                val result = adb.execute(request = it, serial = serial)
                if (result.exitCode != 0) return@withContext null
            }

            val osContext = OSContext.resolveOSContext()
            val darkImage = adb.execute(request = ScreenCaptureRequest(adapter), serial = serial).toBufferedImage()
            val darkImagePath = osContext.directory + FILE_NAME1
            if (!ImageIO.write(darkImage, EXTENSION_NAME, File(darkImagePath))) return@withContext null

            Command.DarkThemeOff().requests.forEach {
                val result = adb.execute(request = it, serial = serial)
                if (result.exitCode != 0) return@withContext null
            }

            val lightImage = adb.execute(request = ScreenCaptureRequest(adapter), serial = serial).toBufferedImage()
            val lightImagePath = osContext.directory + FILE_NAME2
            if (!ImageIO.write(lightImage, EXTENSION_NAME, File(lightImagePath))) return@withContext null

            return@withContext darkImagePath to lightImagePath
        }
    }

    companion object {
        private const val FILE_NAME1 = "screenshot1.png"
        private const val FILE_NAME2 = "screenshot2.png"
        private const val EXTENSION_NAME = "png"
    }
}
