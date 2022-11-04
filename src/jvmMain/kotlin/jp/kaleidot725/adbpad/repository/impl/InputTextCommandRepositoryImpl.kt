package jp.kaleidot725.adbpad.repository.impl

import com.malinskiy.adam.AndroidDebugBridgeClientFactory
import jp.kaleidot725.adbpad.domain.model.Device
import jp.kaleidot725.adbpad.domain.model.InputTextCommand
import jp.kaleidot725.adbpad.domain.model.Setting
import jp.kaleidot725.adbpad.domain.repository.InputTextCommandRepository
import jp.kaleidot725.adbpad.domain.service.SettingFileCreator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class InputTextCommandRepositoryImpl : InputTextCommandRepository {
    private val runningCommands: MutableSet<InputTextCommand> = mutableSetOf()
    private val adbClient = AndroidDebugBridgeClientFactory().build()

    override suspend fun getAllTextCommand(): List<InputTextCommand> {
        return withContext(Dispatchers.IO) {
            val setting = SettingFileCreator.load()
            return@withContext setting.inputTexts.map { text ->
                InputTextCommand(text = text, isRunning = runningCommands.any { it.text == text })
            }
        }
    }

    override suspend fun addTextCommand(command: InputTextCommand): Boolean {
        return withContext(Dispatchers.IO) {
            val oldSetting = SettingFileCreator.load() ?: Setting()
            if (oldSetting.inputTexts.any { it == command.text }) return@withContext true

            val newInputTexts = oldSetting.inputTexts.toMutableList().apply { add(command.text) }
            val newSetting = oldSetting.copy(inputTexts = newInputTexts)
            return@withContext SettingFileCreator.save(newSetting)
        }
    }

    override suspend fun removeTextCommand(command: InputTextCommand): Boolean {
        return withContext(Dispatchers.IO) {
            val oldSetting = SettingFileCreator.load()
            val newInputTexts = oldSetting.inputTexts.toMutableList().apply { remove(command.text) }
            val newSetting = oldSetting.copy(inputTexts = newInputTexts)
            return@withContext SettingFileCreator.save(newSetting)
        }
    }

    override suspend fun sendCommand(
        device: Device,
        command: InputTextCommand,
        onStart: suspend () -> Unit,
        onComplete: suspend () -> Unit,
        onFailed: suspend () -> Unit
    ) {
        withContext(Dispatchers.IO) {
            runningCommands.add(command)
            onStart()

            delay(300)

            command.requests.forEach { request ->
                val result = adbClient.execute(request, device.serial)
                if (result.exitCode != 0) {
                    runningCommands.remove(command)
                    onFailed()
                    return@withContext
                }
            }

            runningCommands.remove(command)
            onComplete()
        }
    }
}
