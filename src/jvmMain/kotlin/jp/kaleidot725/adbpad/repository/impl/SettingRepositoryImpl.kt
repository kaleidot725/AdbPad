package jp.kaleidot725.adbpad.repository.impl

import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.domain.model.setting.Appearance
import jp.kaleidot725.adbpad.domain.model.setting.SdkPath
import jp.kaleidot725.adbpad.domain.model.setting.WindowSize
import jp.kaleidot725.adbpad.domain.repository.SettingRepository
import jp.kaleidot725.adbpad.domain.service.SettingFileCreator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SettingRepositoryImpl : SettingRepository {
    override suspend fun updateAppearance(appearance: Appearance): Boolean {
        return withContext(Dispatchers.IO) {
            val oldSetting = SettingFileCreator.load()
            val newSetting = oldSetting.copy(appearance = appearance)
            return@withContext SettingFileCreator.save(newSetting)
        }
    }

    override suspend fun getAppearance(): Appearance {
        return withContext(Dispatchers.IO) {
            val setting = SettingFileCreator.load()
            return@withContext setting.appearance
        }
    }

    override suspend fun updateSdkPath(sdkPath: SdkPath): Boolean {
        return withContext(Dispatchers.IO) {
            val oldSetting = SettingFileCreator.load()
            val newSetting = oldSetting.copy(sdkPath = sdkPath)
            return@withContext SettingFileCreator.save(newSetting)
        }
    }

    override suspend fun getSdkPath(): SdkPath {
        return withContext(Dispatchers.IO) {
            val setting = SettingFileCreator.load()
            return@withContext setting.sdkPath
        }
    }

    override suspend fun updateWindowSize(size: WindowSize): Boolean {
        return withContext(Dispatchers.IO) {
            val oldSetting = SettingFileCreator.load()
            val newSetting = oldSetting.copy(windowSize = size)
            return@withContext SettingFileCreator.save(newSetting)
        }
    }

    override suspend fun getWindowSize(): WindowSize {
        return withContext(Dispatchers.IO) {
            val setting = SettingFileCreator.load()
            return@withContext setting.windowSize
        }
    }

    override suspend fun updateLanguage(language: Language.Type): Boolean {
        return withContext(Dispatchers.IO) {
            val oldSetting = SettingFileCreator.load()
            val newSetting = oldSetting.copy(language = language)
            return@withContext SettingFileCreator.save(newSetting)
        }
    }

    override suspend fun getLanguage(): Language.Type {
        return withContext(Dispatchers.IO) {
            val setting = SettingFileCreator.load()
            return@withContext setting.language
        }
    }
}
