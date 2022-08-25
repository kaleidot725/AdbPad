package jp.kaleidot725.adbpad.model.usecase

import jp.kaleidot725.adbpad.model.data.InputText
import jp.kaleidot725.adbpad.model.data.Setting
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetInputTextUseCase {
    suspend operator fun invoke(): List<InputText> {
        return withContext(Dispatchers.IO) {
            val setting = Setting.load()
            return@withContext setting?.inputTexts ?: emptyList()
        }
    }
}
