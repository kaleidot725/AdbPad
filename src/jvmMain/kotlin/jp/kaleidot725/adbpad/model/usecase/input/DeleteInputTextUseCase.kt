package jp.kaleidot725.adbpad.model.usecase.input

import jp.kaleidot725.adbpad.model.utils.SettingUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeleteInputTextUseCase {
    suspend operator fun invoke(text: String): Boolean {
        return withContext(Dispatchers.IO) {
            val oldSetting = SettingUtils.load()
            val newInputTexts = oldSetting.inputTexts.toMutableList().apply { remove(text) }
            val newSetting = oldSetting.copy(inputTexts = newInputTexts)
            return@withContext SettingUtils.save(newSetting)
        }
    }
}
