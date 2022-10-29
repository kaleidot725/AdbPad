package jp.kaleidot725.adbpad.domain.usecase.input

import jp.kaleidot725.adbpad.domain.service.SettingFileService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeleteInputTextUseCase {
    suspend operator fun invoke(text: String): Boolean {
        return withContext(Dispatchers.IO) {
            val oldSetting = SettingFileService.load()
            val newInputTexts = oldSetting.inputTexts.toMutableList().apply { remove(text) }
            val newSetting = oldSetting.copy(inputTexts = newInputTexts)
            return@withContext SettingFileService.save(newSetting)
        }
    }
}
