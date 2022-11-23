package jp.kaleidot725.adbpad.repository.impl

import jp.kaleidot725.adbpad.domain.model.setting.SdkPath
import jp.kaleidot725.adbpad.domain.repository.SdkPathRepository
import jp.kaleidot725.adbpad.domain.service.SettingFileCreator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SdkPathRepositoryImpl : SdkPathRepository {
    override suspend fun update(sdkPath: SdkPath): Boolean {
        return withContext(Dispatchers.IO) {
            val oldSetting = SettingFileCreator.load()
            val newSetting = oldSetting.copy(sdkPath = sdkPath)
            return@withContext SettingFileCreator.save(newSetting)
        }
    }

    override suspend fun get(): SdkPath {
        return withContext(Dispatchers.IO) {
            val setting = SettingFileCreator.load()
            return@withContext setting.sdkPath
        }
    }
}