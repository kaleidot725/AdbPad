package jp.kaleidot725.adbpad.repository.impl

import com.malinskiy.adam.AndroidDebugBridgeClientFactory
import jp.kaleidot725.adbpad.domain.model.command.DeviceControlCommand
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.repository.DeviceControlCommandRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeviceControlCommandRepositoryImpl : DeviceControlCommandRepository {
    private val adbClient = AndroidDebugBridgeClientFactory().build()

    override suspend fun sendCommand(
        device: Device,
        command: DeviceControlCommand,
        onStart: suspend () -> Unit,
        onComplete: suspend () -> Unit,
        onFailed: suspend () -> Unit,
    ) {
        withContext(Dispatchers.IO) {
            try {
                onStart()

                command.requests.forEach { request ->
                    val result = adbClient.execute(request, device.serial)
                    if (result.exitCode != 0) {
                        onFailed()
                        return@withContext
                    }
                }

                onComplete()
            } catch (e: Exception) {
                onFailed()
            }
        }
    }
}
