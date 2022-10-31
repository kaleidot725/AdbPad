package jp.kaleidot725.adbpad.domain.usecase.command

import jp.kaleidot725.adbpad.domain.model.Command
import jp.kaleidot725.adbpad.domain.model.Device
import jp.kaleidot725.adbpad.domain.repository.CommandRepository

class ExecuteCommandUseCase(
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
            onStart = onStart,
            onFailed = onFailed,
            onComplete = onComplete
        )
    }
}
