package jp.kaleidot725.adbpad.domain.usecase.command

import jp.kaleidot725.adbpad.domain.model.command.NormalCommand
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.repository.EventRepository
import jp.kaleidot725.adbpad.domain.repository.NormalCommandRepository
import jp.kaleidot725.adbpad.view.model.Event

class ExecuteCommandUseCase(
    private val eventRepository: EventRepository,
    private val normalCommandRepository: NormalCommandRepository
) {
    suspend operator fun invoke(
        device: Device,
        command: NormalCommand,
        onStart: suspend () -> Unit,
        onFailed: suspend () -> Unit,
        onComplete: suspend () -> Unit
    ) {
        normalCommandRepository.sendCommand(
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
