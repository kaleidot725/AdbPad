package jp.kaleidot725.adbpad.repository.impl

import com.malinskiy.adam.AndroidDebugBridgeClientFactory
import jp.kaleidot725.adbpad.domain.model.command.KeyCommand
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.repository.KeyCommandRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class KeyCommandRepositoryImpl : KeyCommandRepository {
    private val adbClient = AndroidDebugBridgeClientFactory().build()

    override suspend fun sendKeyCommand(
        device: Device,
        keycode: Int,
        onStart: suspend () -> Unit,
        onComplete: suspend () -> Unit,
        onFailed: suspend () -> Unit,
    ) {
        withContext(Dispatchers.IO) {
            val command = KeyCommand(keycode)
            command.requests.forEach { request ->
                val result = adbClient.execute(request, device.serial)
                if (result.exitCode != 0) {
                    onFailed()
                    return@withContext
                }
            }

            onComplete()
        }
    }
}
