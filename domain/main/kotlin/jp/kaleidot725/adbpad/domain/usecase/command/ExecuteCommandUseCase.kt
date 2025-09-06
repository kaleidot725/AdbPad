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
        onFailed: suspend () -> Unit,
        onComplete: suspend () -> Unit,
    ) {
        normalCommandRepository.sendCommand(
            device = device,
            command = command,
            onStart = {
                onStart()
            },
            onFailed = {
                onFailed()
            },
            onComplete = {
                onComplete()
            },
        )
    }
}
