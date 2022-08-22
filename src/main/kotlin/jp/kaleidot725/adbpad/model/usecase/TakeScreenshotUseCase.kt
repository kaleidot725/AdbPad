package jp.kaleidot725.adbpad.model.usecase

import com.malinskiy.adam.AndroidDebugBridgeClientFactory
import com.malinskiy.adam.request.framebuffer.RawImageScreenCaptureAdapter
import com.malinskiy.adam.request.framebuffer.ScreenCaptureRequest
import jp.kaleidot725.adbpad.model.data.OperatingSystem
import java.io.File
import javax.imageio.ImageIO

class TakeScreenshotUseCase {
    suspend operator fun invoke(serial: String?): String? {
        val adb = AndroidDebugBridgeClientFactory().build()
        val adapter = RawImageScreenCaptureAdapter()
        val image = adb.execute(request = ScreenCaptureRequest(adapter), serial = serial).toBufferedImage()

        val path = OperatingSystem.resolveOperationSystem().direcotry + "screenshot.png"
        if (!ImageIO.write(image, "png", File(path))) null
        return path
    }
}
