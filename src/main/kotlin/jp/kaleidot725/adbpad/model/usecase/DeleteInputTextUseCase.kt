package jp.kaleidot725.adbpad.model.usecase

import jp.kaleidot725.adbpad.model.data.Setting
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeleteInputTextUseCase {
    suspend operator fun invoke(text: String): Boolean {
        return withContext(Dispatchers.IO) {
            val oldSetting = Setting.load() ?: Setting()
            val newInputTexts = oldSetting.inputTexts.toMutableList().apply { remove(text) }
            val newSetting = oldSetting.copy(inputTexts = newInputTexts)
            return@withContext Setting.write(newSetting)
        }
    }
}
