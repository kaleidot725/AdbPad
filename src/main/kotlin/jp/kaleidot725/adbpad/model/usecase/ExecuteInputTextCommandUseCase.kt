package jp.kaleidot725.adbpad.model.usecase

import com.malinskiy.adam.AndroidDebugBridgeClientFactory
import jp.kaleidot725.adbpad.model.data.Command
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExecuteInputTextCommandUseCase {
    suspend operator fun invoke(serial: String?, text: String): Boolean {
        return withContext(Dispatchers.IO) {
            val adbClient = AndroidDebugBridgeClientFactory().build()
            Command.InputText(text).requests.forEach { request ->
                val result = adbClient.execute(request, serial)
                if (result.exitCode != 0) return@withContext false
            }
            return@withContext true
        }
    }
}
