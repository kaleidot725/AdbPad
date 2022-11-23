package jp.kaleidot725.adbpad.domain.usecase.window

import jp.kaleidot725.adbpad.domain.model.WindowSize
import jp.kaleidot725.adbpad.domain.repository.WindowSizeRepository

class GetWindowSizeUseCase(
    private val windowSizeRepository: WindowSizeRepository
) {
    suspend operator fun invoke(): WindowSize {
        return windowSizeRepository.getWindowSize()
    }
}
