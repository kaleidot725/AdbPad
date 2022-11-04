package jp.kaleidot725.adbpad.domain.usecase.input

import jp.kaleidot725.adbpad.domain.model.Device
import jp.kaleidot725.adbpad.domain.model.Event
import jp.kaleidot725.adbpad.domain.model.InputTextCommand
import jp.kaleidot725.adbpad.domain.repository.EventRepository
import jp.kaleidot725.adbpad.domain.repository.InputTextCommandRepository

class ExecuteInputTextCommandUseCase(
    private val eventRepository: EventRepository,
    private val inputTextCommandRepository: InputTextCommandRepository
) {
    suspend operator fun invoke(
        device: Device,
        command: InputTextCommand,
        onStart: suspend () -> Unit,
        onFailed: suspend () -> Unit,
        onComplete: suspend () -> Unit
    ) {
        inputTextCommandRepository.sendCommand(
            device = device,
            command = command,
            onStart = {
                eventRepository.sendEvent(Event.StartInputTextCommand(command.text))
                onStart()
            },
            onFailed = {
                eventRepository.sendEvent(Event.ErrorInputTextCommand(command.text))
                onFailed()
            },
            onComplete = {
                eventRepository.sendEvent(Event.EndInputTextCommand(command.text))
                onComplete()
            }
        )
    }
}
