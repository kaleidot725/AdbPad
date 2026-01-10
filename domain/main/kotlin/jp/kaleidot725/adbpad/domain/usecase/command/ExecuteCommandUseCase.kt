package jp.kaleidot725.adbpad.domain.usecase.command

import jp.kaleidot725.adbpad.domain.model.command.NormalCommand
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.repository.NormalCommandRepository

class ExecuteCommandUseCase(
    private val normalCommandRepository: NormalCommandRepository,
) {
    suspend operator fun invoke(
        device: Device,
        command: NormalCommand,
        onStart: suspend () -> Unit,
        onFailed: suspend (command: String, output: String) -> Unit,
        onComplete: suspend (command: String, output: String) -> Unit,
    ) {
        normalCommandRepository.sendCommand(
            device = device,
            command = command,
            onStart = {
                onStart()
            },
            onFailed = { formattedCommand, output ->
                onFailed(formattedCommand, output)
            },
            onComplete = { formattedCommand, output ->
                onComplete(formattedCommand, output)
            },
        )
    }
}
