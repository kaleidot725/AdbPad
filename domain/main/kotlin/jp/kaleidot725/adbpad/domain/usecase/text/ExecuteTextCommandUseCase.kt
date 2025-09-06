package jp.kaleidot725.adbpad.domain.usecase.text

import jp.kaleidot725.adbpad.domain.model.command.TextCommand
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.repository.TextCommandRepository

class ExecuteTextCommandUseCase(
    private val textCommandRepository: TextCommandRepository,
) {
    suspend operator fun invoke(
        device: Device,
        command: TextCommand,
        option: TextCommand.Option,
        onStart: suspend () -> Unit,
        onFailed: suspend () -> Unit,
        onComplete: suspend () -> Unit,
    ) {
        textCommandRepository.sendCommand(
            device = device,
            command = command,
            option = option,
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
