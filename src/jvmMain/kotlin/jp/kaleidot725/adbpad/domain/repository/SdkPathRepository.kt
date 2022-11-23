package jp.kaleidot725.adbpad.domain.repository

import jp.kaleidot725.adbpad.domain.model.setting.SdkPath

interface SdkPathRepository {
    suspend fun update(sdkPath: SdkPath): Boolean
    suspend fun get(): SdkPath
}