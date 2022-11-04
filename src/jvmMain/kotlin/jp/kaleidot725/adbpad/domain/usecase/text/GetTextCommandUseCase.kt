package jp.kaleidot725.adbpad.domain.usecase.text

import jp.kaleidot725.adbpad.domain.model.command.TextCommand
import jp.kaleidot725.adbpad.domain.repository.TextCommandRepository

class GetTextCommandUseCase(private val textCommandRepository: TextCommandRepository) {
    suspend operator fun invoke(): List<TextCommand> {
        return textCommandRepository.getAllTextCommand()
    }
}
