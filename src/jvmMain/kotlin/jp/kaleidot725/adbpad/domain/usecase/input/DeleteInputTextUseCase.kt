package jp.kaleidot725.adbpad.domain.usecase.input

import jp.kaleidot725.adbpad.domain.repository.TextRepository

class DeleteInputTextUseCase(private val textRepository: TextRepository) {
    suspend operator fun invoke(text: String): Boolean {
        return textRepository.removeText(text)
    }
}
