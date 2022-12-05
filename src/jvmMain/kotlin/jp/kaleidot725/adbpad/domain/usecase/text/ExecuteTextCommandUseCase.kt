package jp.kaleidot725.adbpad.domain.usecase.text

import jp.kaleidot725.adbpad.domain.model.command.TextCommand
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.model.log.Event
import jp.kaleidot725.adbpad.domain.repository.EventRepository
import jp.kaleidot725.adbpad.domain.repository.TextCommandRepository

class ExecuteTextCommandUseCase(
    private val eventRepository: EventRepository,
    private val textCommandRepository: TextCommandRepository
) {
    suspend operator fun invoke(
        device: Device,
        command: TextCommand,
        onStart: suspend () -> Unit,
        onFailed: suspend () -> Unit,
        onComplete: suspend () -> Unit
    ) {
        textCommandRepository.sendCommand(
            device = device,
            command = command,
            onStart = {
                eventRepository.sendEvent(Event.StartSendTextCommand(command.text))
                onStart()
            },
            onFailed = {
                eventRepository.sendEvent(Event.ErrorSendTextCommand(command.text))
                onFailed()
            },
            onComplete = {
                eventRepository.sendEvent(Event.EndSendTextCommand(command.text))
                onComplete()
            }
        )
    }
}
