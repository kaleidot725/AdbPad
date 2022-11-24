package jp.kaleidot725.adbpad.domain.usecase.adb

import com.malinskiy.adam.interactor.StartAdbInteractor
import jp.kaleidot725.adbpad.domain.repository.SdkPathRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

class StartAdbUseCase(
    private val sdkPathRepository: SdkPathRepository
) {
    suspend operator fun invoke(): Boolean {
        return withContext(Dispatchers.IO) {
            val sdkPath = sdkPathRepository.get()
            return@withContext StartAdbInteractor().execute(
                adbBinary = File(sdkPath.adbDirectory),
                androidHome = File(sdkPath.androidSdkDirectory),
                serverPort = sdkPath.adbServerPort
            )
        }
    }
}
