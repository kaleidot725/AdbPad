package jp.kaleidot725.adbpad.domain.usecase.input

import jp.kaleidot725.adbpad.domain.repository.InputTextCommandRepository
import jp.kaleidot725.adbpad.domain.service.InputTextCommandFactory

class AddInputTextCommandUseCase(private val inputTextCommandRepository: InputTextCommandRepository) {
    suspend operator fun invoke(text: String): Boolean {
        return inputTextCommandRepository.addTextCommand(InputTextCommandFactory.createNew(text))
    }
}
