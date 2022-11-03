package jp.kaleidot725.adbpad.domain.usecase.input

import jp.kaleidot725.adbpad.domain.repository.TextRepository

class AddInputTextUseCase(private val textRepository: TextRepository) {
    suspend operator fun invoke(text: String): Boolean {
        return textRepository.addText(text)
    }
}
