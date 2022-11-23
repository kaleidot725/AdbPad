package jp.kaleidot725.adbpad.domain.usecase.window

import jp.kaleidot725.adbpad.domain.model.setting.WindowSize
import jp.kaleidot725.adbpad.domain.repository.WindowSizeRepository

class SaveWindowSizeUseCase(
    private val windowSizeRepository: WindowSizeRepository
) {
    suspend operator fun invoke(size: WindowSize): Boolean {
        return windowSizeRepository.updateWindowSize(size)
    }
}
