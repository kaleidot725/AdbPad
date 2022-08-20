package jp.kaleidot725.adbpad.model.usecase

import com.malinskiy.adam.interactor.StartAdbInteractor

class StartAdbUseCase {
    suspend operator fun invoke(): Boolean {
        return StartAdbInteractor().execute()
    }
}