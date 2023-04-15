package jp.kaleidot725.adbpad.domain.usecase.screenshot

import jp.kaleidot725.adbpad.domain.model.command.ScreenshotCommand
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.model.log.Event
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
}
