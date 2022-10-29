package jp.kaleidot725.adbpad.domain.usecase.screenshot

import com.malinskiy.adam.AndroidDebugBridgeClientFactory
import com.malinskiy.adam.request.framebuffer.RawImageScreenCaptureAdapter
import com.malinskiy.adam.request.framebuffer.ScreenCaptureRequest
import jp.kaleidot725.adbpad.domain.model.OSContext
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

            val osContext = OSContext.resolveOSContext()
            val path = osContext.directory + FILE_NAME
            val isSuccess = ImageIO.write(image, EXTENSION_NAME, File(path))
            return@withContext if (isSuccess) path else null
        }
    }

    companion object {
        private const val FILE_NAME = "screenshot.png"
        private const val EXTENSION_NAME = "png"
    }
}
