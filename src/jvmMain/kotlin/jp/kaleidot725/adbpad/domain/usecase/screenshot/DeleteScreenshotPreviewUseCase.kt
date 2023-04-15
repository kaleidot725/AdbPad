package jp.kaleidot725.adbpad.domain.usecase.screenshot

import jp.kaleidot725.adbpad.domain.model.log.Event
import jp.kaleidot725.adbpad.domain.repository.EventRepository
import jp.kaleidot725.adbpad.domain.repository.ScreenshotCommandRepository

class DeleteScreenshotPreviewUseCase(
    private val eventRepository: EventRepository,
    private val screenshotCommandRepository: ScreenshotCommandRepository
) {
    suspend operator fun invoke() {
        eventRepository.sendEvent(Event.ClearScreenshotCache)
        return screenshotCommandRepository.deleteScreenshotCache()
    }
}
