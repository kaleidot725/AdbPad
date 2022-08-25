package jp.kaleidot725.adbpad.model.usecase

import jp.kaleidot725.adbpad.model.data.InputText
import jp.kaleidot725.adbpad.model.data.Setting
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeleteInputTextUseCase {
    suspend operator fun invoke(inputText: InputText): Boolean {
        return withContext(Dispatchers.IO) {
            val oldSetting = Setting.load() ?: Setting()
            val newInputTexts = oldSetting.inputTexts.toMutableList().apply { remove(inputText) }
            val newSetting = oldSetting.copy(inputTexts = newInputTexts)
            return@withContext Setting.write(newSetting)
        }
    }
}
