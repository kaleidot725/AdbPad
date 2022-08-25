package jp.kaleidot725.adbpad.model.usecase

import com.malinskiy.adam.interactor.StartAdbInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StartAdbUseCase {
    suspend operator fun invoke(): Boolean {
        return withContext(Dispatchers.IO) {
            return@withContext StartAdbInteractor().execute()
        }
    }
}
