package jp.kaleidot725.adbpad.domain.usecase.text

import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.model.log.Event
import jp.kaleidot725.adbpad.domain.repository.EventRepository
import jp.kaleidot725.adbpad.domain.repository.TextCommandRepository

class SendUserInputTextCommandUseCase(
    private val eventRepository: EventRepository,
    private val textCommandRepository: TextCommandRepository,
) {
    suspend operator fun invoke(
        device: Device,
        text: String,
        onStart: suspend () -> Unit,
        onFailed: suspend () -> Unit,
        onComplete: suspend () -> Unit,
    ) {
        textCommandRepository.sendUserInputText(
            device = device,
            text = text,
            onStart = {
                eventRepository.sendEvent(Event.StartSendTextCommand(text))
                onStart()
            },
            onFailed = {
                eventRepository.sendEvent(Event.ErrorSendTextCommand(text))
                onFailed()
            },
            onComplete = {
                eventRepository.sendEvent(Event.EndSendTextCommand(text))
                onComplete()
            },
        )
    }
}
