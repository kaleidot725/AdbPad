package jp.kaleidot725.adbpad.model.usecase

import jp.kaleidot725.adbpad.model.utils.SettingUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetInputTextUseCase {
    suspend operator fun invoke(): List<String> {
        return withContext(Dispatchers.IO) {
            val setting = SettingUtils.load()
            return@withContext setting.inputTexts
        }
    }
}
