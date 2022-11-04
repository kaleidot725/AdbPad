package jp.kaleidot725.adbpad.domain.usecase.input

import jp.kaleidot725.adbpad.domain.model.InputTextCommand
import jp.kaleidot725.adbpad.domain.repository.InputTextCommandRepository

class DeleteInputTextCommandUseCase(private val inputTextCommandRepository: InputTextCommandRepository) {
    suspend operator fun invoke(command: InputTextCommand): Boolean {
        return inputTextCommandRepository.removeTextCommand(command)
    }
}
