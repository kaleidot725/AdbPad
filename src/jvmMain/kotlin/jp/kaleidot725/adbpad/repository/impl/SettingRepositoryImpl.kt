package jp.kaleidot725.adbpad.repository.impl

import jp.kaleidot725.adbpad.domain.repository.SettingRepository
import jp.kaleidot725.adbpad.domain.service.SettingFileCreator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SettingRepositoryImpl : SettingRepository {
    override suspend fun updateAdbDirectory(adbDirectory: String): Boolean {
        return withContext(Dispatchers.IO) {
            val oldSetting = SettingFileCreator.load()
            val newSetting = oldSetting.copy(adbDirectory = adbDirectory)
            return@withContext SettingFileCreator.save(newSetting)
        }
    }

    override suspend fun getAdbDirectory(): String {
        return withContext(Dispatchers.IO) {
            val setting = SettingFileCreator.load()
            return@withContext setting.adbDirectory
        }
    }

    override suspend fun updateAdbServerPort(adbServerPort: Int): Boolean {
        return withContext(Dispatchers.IO) {
            val oldSetting = SettingFileCreator.load()
            val newSetting = oldSetting.copy(adbServerPort = adbServerPort)
            return@withContext SettingFileCreator.save(newSetting)
        }
    }

    override suspend fun getAdbServerPort(): Int {
        return withContext(Dispatchers.IO) {
            val setting = SettingFileCreator.load()
            return@withContext setting.adbServerPort
        }
    }

    override suspend fun updateAndroidSdkDirectory(androidSdkDirectory: String): Boolean {
        return withContext(Dispatchers.IO) {
            val oldSetting = SettingFileCreator.load()
            val newSetting = oldSetting.copy(androidSdkDirectory = androidSdkDirectory)
            return@withContext SettingFileCreator.save(newSetting)
        }
    }

    override suspend fun getAndroidSdkDirectory(): String {
        return withContext(Dispatchers.IO) {
            val setting = SettingFileCreator.load()
            return@withContext setting.androidSdkDirectory
        }
    }

}