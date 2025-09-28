package jp.kaleidot725.adbpad.domain.usecase.screenshot

import jp.kaleidot725.adbpad.domain.model.command.ScreenshotCommand
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.repository.ScreenshotCommandRepository
import java.io.File

class TakeScreenshotUseCase(
    private val screenshotCommandRepository: ScreenshotCommandRepository,
) {
    suspend operator fun invoke(
        device: Device,
        command: ScreenshotCommand,
        onStart: suspend () -> Unit,
        onFailed: suspend () -> Unit,
        onComplete: suspend (File) -> Unit,
    ) {
        screenshotCommandRepository.captureScreenshot(
            device = device,
            command = command,
            onStart = {
                onStart()
            },
            onFailed = {
                onFailed()
            },
            onComplete = {
                onComplete(it)
            },
        )
    }
}
