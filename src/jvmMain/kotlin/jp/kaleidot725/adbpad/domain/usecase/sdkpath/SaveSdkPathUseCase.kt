package jp.kaleidot725.adbpad.domain.usecase.sdkpath

import jp.kaleidot725.adbpad.domain.model.setting.SdkPath
import jp.kaleidot725.adbpad.domain.repository.SdkPathRepository

class SaveSdkPathUseCase(
    private val sdkPathRepository: SdkPathRepository
) {
    suspend operator fun invoke(
        adbDirectory: String,
        adbServerPort: Int?,
        androidSdkDirectory: String
    ): Boolean {
        val sdkPath = SdkPath(adbDirectory, adbServerPort ?: 30000, androidSdkDirectory)
        return sdkPathRepository.update(sdkPath)
    }
}
