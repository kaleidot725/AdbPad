package jp.kaleidot725.adbpad.repository.impl

import jp.kaleidot725.adbpad.domain.model.Setting
import jp.kaleidot725.adbpad.domain.repository.TextRepository
import jp.kaleidot725.adbpad.domain.service.SettingFileCreator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TextRepositoryImpl : TextRepository {
    override suspend fun getAllText(): List<String> {
        return withContext(Dispatchers.IO) {
            val setting = SettingFileCreator.load()
            return@withContext setting.inputTexts
        }
    }

    override suspend fun addText(text: String): Boolean {
        return withContext(Dispatchers.IO) {
            val oldSetting = SettingFileCreator.load() ?: Setting()
            if (oldSetting.inputTexts.any { it == text }) return@withContext true

            val newInputTexts = oldSetting.inputTexts.toMutableList().apply { add(text) }
            val newSetting = oldSetting.copy(inputTexts = newInputTexts)
            return@withContext SettingFileCreator.save(newSetting)
        }
    }

    override suspend fun removeText(text: String): Boolean {
        return withContext(Dispatchers.IO) {
            val oldSetting = SettingFileCreator.load()
            val newInputTexts = oldSetting.inputTexts.toMutableList().apply { remove(text) }
            val newSetting = oldSetting.copy(inputTexts = newInputTexts)
            return@withContext SettingFileCreator.save(newSetting)
        }
    }
}