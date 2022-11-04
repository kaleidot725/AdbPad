package jp.kaleidot725.adbpad.domain.usecase.text

import jp.kaleidot725.adbpad.domain.repository.TextCommandRepository
import jp.kaleidot725.adbpad.domain.service.TextCommandFactory

class AddTextCommandUseCase(private val textCommandRepository: TextCommandRepository) {
    suspend operator fun invoke(text: String): Boolean {
        return textCommandRepository.addTextCommand(TextCommandFactory.createNew(text))
    }
}
