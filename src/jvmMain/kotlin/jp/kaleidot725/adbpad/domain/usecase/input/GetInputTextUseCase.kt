package jp.kaleidot725.adbpad.domain.usecase.input

import jp.kaleidot725.adbpad.domain.repository.TextRepository

class GetInputTextUseCase(private val textRepository: TextRepository) {
    suspend operator fun invoke(): List<String> {
        return textRepository.getAllText()
    }
}
