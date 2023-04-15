package jp.kaleidot725.adbpad.domain.usecase.screenshot

import jp.kaleidot725.adbpad.domain.model.log.Event
import jp.kaleidot725.adbpad.domain.repository.EventRepository
import jp.kaleidot725.adbpad.domain.repository.ScreenshotCommandRepository
import jp.kaleidot725.adbpad.utils.ClipBoardUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.imageio.ImageIO


class CopyScreenshotToClipboardUseCase(
    private val eventRepository: EventRepository,
    private val screenshotCommandRepository: ScreenshotCommandRepository
) {
    suspend operator fun invoke() {
        withContext(Dispatchers.IO) {
            val preview = screenshotCommandRepository.getPreview()
            val target = preview.file ?: return@withContext
            ClipBoardUtils.copyFile(target)
            eventRepository.sendEvent(Event.CopyScreenshotToClipBoard)
        }
    }
}
