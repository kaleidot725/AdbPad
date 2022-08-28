package jp.kaleidot725.adbpad.model.usecase

import com.malinskiy.adam.AndroidDebugBridgeClientFactory
import jp.kaleidot725.adbpad.model.data.Command
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExecuteCommandUseCase {
    suspend operator fun invoke(serial: String?, command: Command): Boolean {
        return withContext(Dispatchers.IO) {
            val adbClient = AndroidDebugBridgeClientFactory().build()
            command.requests.forEach { request ->
                val result = adbClient.execute(request, serial)
                if (result.exitCode != 0) return@withContext false
            }
            return@withContext true
        }
    }
}
