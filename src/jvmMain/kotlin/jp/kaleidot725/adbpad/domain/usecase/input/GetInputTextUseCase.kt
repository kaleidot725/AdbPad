package jp.kaleidot725.adbpad.domain.usecase.input

import jp.kaleidot725.adbpad.domain.model.InputTextCommand
import jp.kaleidot725.adbpad.domain.repository.InputTextCommandRepository

class GetInputTextUseCase(private val inputTextCommandRepository: InputTextCommandRepository) {
    suspend operator fun invoke(): List<InputTextCommand> {
        return inputTextCommandRepository.getAllTextCommand()
    }
}
