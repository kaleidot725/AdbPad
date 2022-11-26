package jp.kaleidot725.adbpad.domain.repository

import jp.kaleidot725.adbpad.domain.model.setting.SdkPath
import jp.kaleidot725.adbpad.domain.model.setting.WindowSize

interface SettingRepository {
    suspend fun updateWindowSize(size: WindowSize): Boolean
    suspend fun getWindowSize(): WindowSize
    suspend fun updateSdkPath(sdkPath: SdkPath): Boolean
    suspend fun getSdkPath(): SdkPath
}