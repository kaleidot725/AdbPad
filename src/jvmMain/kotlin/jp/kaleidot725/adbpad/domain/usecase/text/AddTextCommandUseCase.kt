package jp.kaleidot725.adbpad.domain.usecase.text

import jp.kaleidot725.adbpad.domain.repository.TextCommandRepository
import jp.kaleidot725.adbpad.domain.service.TextCommandFactory

class AddTextCommandUseCase(
    private val textCommandRepository: TextCommandRepository,
) {
    suspend operator fun invoke(
        title: String,
        text: String,
    ): Boolean = textCommandRepository.addTextCommand(TextCommandFactory.createNew(title, text))
}
