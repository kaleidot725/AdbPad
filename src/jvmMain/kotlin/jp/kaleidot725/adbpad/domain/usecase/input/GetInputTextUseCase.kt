package jp.kaleidot725.adbpad.domain.usecase.input

import jp.kaleidot725.adbpad.domain.service.SettingFileService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetInputTextUseCase {
    suspend operator fun invoke(): List<String> {
        return withContext(Dispatchers.IO) {
            val setting = SettingFileService.load()
            return@withContext setting.inputTexts
        }
    }
}
