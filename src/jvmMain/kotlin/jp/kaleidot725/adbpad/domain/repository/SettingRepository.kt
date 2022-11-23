package jp.kaleidot725.adbpad.domain.repository

interface SettingRepository {
    suspend fun updateAdbDirectory(adbDirectory: String): Boolean
    suspend fun getAdbDirectory(): String

    suspend fun updateAdbServerPort(adbServerPort: Int): Boolean
    suspend fun getAdbServerPort(): Int

    suspend fun updateAndroidSdkDirectory(androidSdkDirectory: String): Boolean
    suspend fun getAndroidSdkDirectory(): String
}