package jp.kaleidot725.adbpad.model.usecase

import com.malinskiy.adam.AndroidDebugBridgeClientFactory
import com.malinskiy.adam.request.framebuffer.RawImageScreenCaptureAdapter
import com.malinskiy.adam.request.framebuffer.ScreenCaptureRequest
import com.malinskiy.adam.request.shell.v1.ShellCommandRequest
import jp.kaleidot725.adbpad.model.data.OperatingSystem
import java.io.File
import javax.imageio.ImageIO

class TakeThemeScreenshotUseCase {
    suspend operator fun invoke(serial: String?): Pair<String?, String?> {
        val adb = AndroidDebugBridgeClientFactory().build()

        adb.execute(
            request = ShellCommandRequest("cmd uimode night yes"),
            serial = serial
        )

        val adapter = RawImageScreenCaptureAdapter()
        val image1 = adb.execute(request = ScreenCaptureRequest(adapter), serial = serial).toBufferedImage()
        val path1 = OperatingSystem.resolveOperationSystem().direcotry + "screenshot1.png"
        if (!ImageIO.write(image1, "png", File(path1))) return null to null

        adb.execute(
            request = ShellCommandRequest("cmd uimode night no"),
            serial = serial
        )

        val image2 = adb.execute(request = ScreenCaptureRequest(adapter), serial = serial).toBufferedImage()
        val path2 = OperatingSystem.resolveOperationSystem().direcotry + "screenshot2.png"
        if (!ImageIO.write(image2, "png", File(path2))) return path1 to null

        return path1 to path2
    }
}
