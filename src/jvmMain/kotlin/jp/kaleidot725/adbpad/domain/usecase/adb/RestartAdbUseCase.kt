package jp.kaleidot725.adbpad.domain.usecase.adb

import com.malinskiy.adam.interactor.StartAdbInteractor
import com.malinskiy.adam.interactor.StopAdbInteractor
import jp.kaleidot725.adbpad.domain.repository.SettingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.io.File

class RestartAdbUseCase(
    private val settingRepository: SettingRepository,
) {
    suspend operator fun invoke(): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val sdkPath = settingRepository.getSdkPath()
                StopAdbInteractor().execute(
                    adbBinary = File(sdkPath.adbDirectory),
                    serverPort = sdkPath.adbServerPort,
                )

                delay(100)

                return@withContext StartAdbInteractor().execute(
                    adbBinary = File(sdkPath.adbDirectory),
                    serverPort = sdkPath.adbServerPort,
                )
            } catch (e: Exception) {
                println(e)
                return@withContext false
            }
        }
    }
}
