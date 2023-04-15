package jp.kaleidot725.adbpad.domain.usecase.screenshot

import jp.kaleidot725.adbpad.domain.model.log.Event
import jp.kaleidot725.adbpad.domain.model.screenshot.Screenshot
import jp.kaleidot725.adbpad.domain.repository.EventRepository
import jp.kaleidot725.adbpad.domain.repository.ScreenshotCommandRepository
import jp.kaleidot725.adbpad.utils.ClipBoardUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CopyScreenshotToClipboardUseCase(
    private val eventRepository: EventRepository,
    private val screenshotCommandRepository: ScreenshotCommandRepository
) {
    suspend operator fun invoke() {
        withContext(Dispatchers.IO) {
            val cache = screenshotCommandRepository.getScreenshotCache()
            if (cache != Screenshot.EMPTY) {
                ClipBoardUtils.copyFile(cache.file!!)
                eventRepository.sendEvent(Event.CopyScreenshotToClipBoard)
            } else {
                eventRepository.sendEvent(Event.CantCopyScreenshotToClipBoard)
            }
        }
    }
}
