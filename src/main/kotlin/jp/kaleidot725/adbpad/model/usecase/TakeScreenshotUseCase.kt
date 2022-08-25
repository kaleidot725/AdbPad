package jp.kaleidot725.adbpad.model.usecase

import com.malinskiy.adam.AndroidDebugBridgeClientFactory
import com.malinskiy.adam.request.framebuffer.RawImageScreenCaptureAdapter
import com.malinskiy.adam.request.framebuffer.ScreenCaptureRequest
import jp.kaleidot725.adbpad.model.data.OSContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import javax.imageio.ImageIO

class TakeScreenshotUseCase {
    suspend operator fun invoke(serial: String?): String? {
        return withContext(Dispatchers.IO) {
            val adb = AndroidDebugBridgeClientFactory().build()
            val adapter = RawImageScreenCaptureAdapter()
            val image = adb.execute(request = ScreenCaptureRequest(adapter), serial = serial).toBufferedImage()

            val path = OSContext.resolveOSContext().direcotry + "screenshot.png"
            val isSuccess = ImageIO.write(image, "png", File(path))
            return@withContext if (isSuccess) path else null
        }
    }
}
