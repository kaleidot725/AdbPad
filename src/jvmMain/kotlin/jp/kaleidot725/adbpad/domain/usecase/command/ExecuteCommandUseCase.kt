package jp.kaleidot725.adbpad.domain.usecase.command

import jp.kaleidot725.adbpad.domain.model.Device
import jp.kaleidot725.adbpad.domain.model.Event
import jp.kaleidot725.adbpad.domain.model.command.Command
import jp.kaleidot725.adbpad.domain.repository.CommandRepository
import jp.kaleidot725.adbpad.domain.repository.EventRepository

class ExecuteCommandUseCase(
    private val eventRepository: EventRepository,
    private val commandRepository: CommandRepository
) {
    suspend operator fun invoke(
        device: Device,
        command: Command,
        onStart: suspend () -> Unit,
        onFailed: suspend () -> Unit,
        onComplete: suspend () -> Unit
    ) {
        commandRepository.sendCommand(
            device = device,
            command = command,
            onStart = {
                eventRepository.sendEvent(Event.StartCommand(command.title))
                onStart()
            },
            onFailed = {
                eventRepository.sendEvent(Event.ErrorCommand(command.title))
                onFailed()
            },
            onComplete = {
                eventRepository.sendEvent(Event.EndCommand(command.title))
                onComplete()
            }
        )
    }
}
