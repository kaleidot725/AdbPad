package jp.kaleidot725.adbpad.domain.usecase.screenshot

import jp.kaleidot725.adbpad.domain.model.screenshot.ScreenshotPreview
import jp.kaleidot725.adbpad.domain.repository.ScreenshotCommandRepository

class GetScreenshotPreviewUseCase(private val screenshotCommandRepository: ScreenshotCommandRepository) {
    operator fun invoke(): ScreenshotPreview {
        return screenshotCommandRepository.getPreview()
    }
}
