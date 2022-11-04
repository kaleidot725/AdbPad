package jp.kaleidot725.adbpad.domain.usecase.text

import jp.kaleidot725.adbpad.domain.model.command.TextCommand
import jp.kaleidot725.adbpad.domain.repository.TextCommandRepository

class DeleteTextCommandUseCase(private val textCommandRepository: TextCommandRepository) {
    suspend operator fun invoke(command: TextCommand): Boolean {
        return textCommandRepository.removeTextCommand(command)
    }
}
