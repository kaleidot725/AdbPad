package jp.kaleidot725.adbpad.domain.usecase.text

import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.model.log.Event
import jp.kaleidot725.adbpad.domain.repository.EventRepository
import jp.kaleidot725.adbpad.domain.repository.KeyCommandRepository

class SendTabCommandUseCase(
    private val eventRepository: EventRepository,
    private val keyCommandRepository: KeyCommandRepository,
) {
    suspend operator fun invoke(
        device: Device,
        onStart: suspend () -> Unit,
        onFailed: suspend () -> Unit,
        onComplete: suspend () -> Unit,
    ) {
        val tabKeyCode = 61
        keyCommandRepository.sendKeyCommand(
            device = device,
            keycode = tabKeyCode,
            onStart = {
                eventRepository.sendEvent(Event.StartSendKeyCommand(tabKeyCode))
                onStart()
            },
            onFailed = {
                eventRepository.sendEvent(Event.ErrorSendKeyCommand(tabKeyCode))
                onFailed()
            },
            onComplete = {
                eventRepository.sendEvent(Event.EndSendKeyCommand(tabKeyCode))
                onComplete()
            },
        )
    }
}
