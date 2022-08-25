package jp.kaleidot725.adbpad.model.usecase

import com.malinskiy.adam.AndroidDebugBridgeClientFactory
import com.malinskiy.adam.request.framebuffer.RawImageScreenCaptureAdapter
import com.malinskiy.adam.request.framebuffer.ScreenCaptureRequest
import com.malinskiy.adam.request.shell.v1.ShellCommandRequest
import jp.kaleidot725.adbpad.model.data.OSContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import javax.imageio.ImageIO

class TakeThemeScreenshotUseCase {
    suspend operator fun invoke(serial: String?): Pair<String, String>? {
        return withContext(Dispatchers.IO) {
            val adb = AndroidDebugBridgeClientFactory().build()
            val adapter = RawImageScreenCaptureAdapter()

            adb.execute(
                request = ShellCommandRequest("cmd uimode night yes"),
                serial = serial
            )

            val darkImage = adb.execute(request = ScreenCaptureRequest(adapter), serial = serial).toBufferedImage()
            val darkImagePath = OSContext.resolveOSContext().direcotry + "screenshot1.png"
            if (!ImageIO.write(darkImage, "png", File(darkImagePath))) return@withContext null

            adb.execute(
                request = ShellCommandRequest("cmd uimode night no"),
                serial = serial
            )

            val lightImage = adb.execute(request = ScreenCaptureRequest(adapter), serial = serial).toBufferedImage()
            val lightImagePath = OSContext.resolveOSContext().direcotry + "screenshot2.png"
            if (!ImageIO.write(lightImage, "png", File(lightImagePath))) return@withContext null

            return@withContext darkImagePath to lightImagePath
        }
    }
}
