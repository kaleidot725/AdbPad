package jp.kaleidot725.adbpad.domain.usecase.window

import jp.kaleidot725.adbpad.domain.repository.WindowSizeRepository
import jp.kaleidot725.adbpad.view.model.WindowSize

class SaveWindowSizeUseCase(
    private val windowSizeRepository: WindowSizeRepository
) {
    suspend operator fun invoke(size: WindowSize): Boolean {
        return windowSizeRepository.updateWindowSize(size)
    }
}
