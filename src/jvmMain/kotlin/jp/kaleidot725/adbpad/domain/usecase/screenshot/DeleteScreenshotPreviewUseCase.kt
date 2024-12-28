package jp.kaleidot725.adbpad.domain.usecase.screenshot

import jp.kaleidot725.adbpad.domain.repository.ScreenshotCommandRepository

class DeleteScreenshotPreviewUseCase(
    private val screenshotCommandRepository: ScreenshotCommandRepository,
) {
    suspend operator fun invoke() {
        return screenshotCommandRepository.deleteScreenshotCache()
    }
}
