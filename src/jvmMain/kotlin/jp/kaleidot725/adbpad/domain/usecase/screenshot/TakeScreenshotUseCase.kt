package jp.kaleidot725.adbpad.domain.usecase.screenshot

import jp.kaleidot725.adbpad.domain.model.Event
import jp.kaleidot725.adbpad.domain.model.command.ScreenshotCommand
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.repository.EventRepository
import jp.kaleidot725.adbpad.domain.repository.ScreenshotCommandRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TakeScreenshotUseCase(
    private val eventRepository: EventRepository,
    private val screenshotCommandRepository: ScreenshotCommandRepository
) {
    suspend operator fun invoke(
        device: Device,
        command: ScreenshotCommand,
        onStart: suspend () -> Unit,
        onFailed: suspend () -> Unit,
        onComplete: suspend () -> Unit
    ) {
        withContext(Dispatchers.IO) {
            screenshotCommandRepository.sendCommand(
                device = device,
                command = command,
                onStart = {
                    eventRepository.sendEvent(Event.StartSendScreenshotCommand)
                    onStart()
                },
                onFailed = {
                    eventRepository.sendEvent(Event.ErrorSendScreenshotCommand)
                    onFailed()
                },
                onComplete = {
                    eventRepository.sendEvent(Event.EndSendScreenshotCommand)
                    onComplete()
                }
            )
        }
    }

//            val adb = AndroidDebugBridgeClientFactory().build()
//            val adapter = RawImageScreenCaptureAdapter()
//            val image = adb.execute(request = ScreenCaptureRequest(adapter), serial = serial).toBufferedImage()
//
//            val osContext = OSContext.resolveOSContext()
//            val path = osContext.directory + FILE_NAME
//            val isSuccess = ImageIO.write(image, EXTENSION_NAME, File(path))
//            return@withContext if (isSuccess) path else null

//    companion object {
//        private const val FILE_NAME = "screenshot.png"
//        private const val EXTENSION_NAME = "png"
//    }
}
