package jp.kaleidot725.adbpad.model.usecase

import jp.kaleidot725.adbpad.model.data.Setting
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetInputTextUseCase {
    suspend operator fun invoke(): List<String> {
        return withContext(Dispatchers.IO) {
            val setting = Setting.load()
            return@withContext setting?.inputTexts ?: emptyList()
        }
    }
}
