package jp.kaleidot725.adbpad.domain.usecase.sdkpath

import jp.kaleidot725.adbpad.domain.model.setting.SdkPath
import jp.kaleidot725.adbpad.domain.repository.SdkPathRepository

class GetSdkPathUseCase(
    private val sdkPathRepository: SdkPathRepository
) {
    suspend operator fun invoke(): SdkPath {
        return sdkPathRepository.get()
    }
}
