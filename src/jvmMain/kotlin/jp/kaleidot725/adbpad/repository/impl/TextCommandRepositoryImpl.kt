package jp.kaleidot725.adbpad.repository.impl

import com.malinskiy.adam.AndroidDebugBridgeClientFactory
import jp.kaleidot725.adbpad.domain.model.command.TextCommand
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.repository.TextCommandRepository
import jp.kaleidot725.adbpad.domain.service.SettingFileCreator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class TextCommandRepositoryImpl : TextCommandRepository {
    private val runningCommands: MutableSet<TextCommand> = mutableSetOf()
    private val adbClient = AndroidDebugBridgeClientFactory().build()

    override suspend fun getAllTextCommand(): List<TextCommand> {
        return withContext(Dispatchers.IO) {
            val setting = SettingFileCreator.load()
            return@withContext setting.textCommandIdList.map { text ->
                text.copy(isRunning = runningCommands.any { it.id == text.id })
            }
        }
    }

    override suspend fun addTextCommand(command: TextCommand): Boolean {
        return withContext(Dispatchers.IO) {
            val oldSetting = SettingFileCreator.load()
            val newInputTexts = oldSetting.textCommandIdList.toMutableList().apply { add(command) }
            val newSetting = oldSetting.copy(textCommandIdList = newInputTexts)
            return@withContext SettingFileCreator.save(newSetting)
        }
    }

    override suspend fun removeTextCommand(command: TextCommand): Boolean {
        return withContext(Dispatchers.IO) {
            val oldSetting = SettingFileCreator.load()
            val newInputTexts = oldSetting.textCommandIdList.toMutableList().apply { remove(command) }
            val newSetting = oldSetting.copy(textCommandIdList = newInputTexts)
            return@withContext SettingFileCreator.save(newSetting)
        }
    }

    override suspend fun updateTextCommandTitle(
        id: String,
        title: String,
    ): Boolean {
        return withContext(Dispatchers.IO) {
            val oldSetting = SettingFileCreator.load()
            val targetIndex = oldSetting.textCommandIdList.indexOfFirst { it.id == id }
            val target = oldSetting.textCommandIdList.getOrNull(targetIndex) ?: return@withContext false
            val newTarget = target.copy(title = title)
            val newCommands = oldSetting.textCommandIdList.toMutableList()
            newCommands.remove(target)
            newCommands.add(targetIndex, newTarget)

            val newSetting = oldSetting.copy(textCommandIdList = newCommands)
            return@withContext SettingFileCreator.save(newSetting)
        }
    }

    override suspend fun updateTextCommandValue(
        id: String,
        text: String,
    ): Boolean {
        return withContext(Dispatchers.IO) {
            val oldSetting = SettingFileCreator.load()
            val targetIndex = oldSetting.textCommandIdList.indexOfFirst { it.id == id }
            val target = oldSetting.textCommandIdList.getOrNull(targetIndex) ?: return@withContext false
            val newTarget = target.copy(text = text)
            val newCommands = oldSetting.textCommandIdList.toMutableList()
            newCommands.remove(target)
            newCommands.add(targetIndex, newTarget)

            val newSetting = oldSetting.copy(textCommandIdList = newCommands)
            return@withContext SettingFileCreator.save(newSetting)
        }
    }

    override suspend fun sendCommand(
        device: Device,
        command: TextCommand,
        onStart: suspend () -> Unit,
        onComplete: suspend () -> Unit,
        onFailed: suspend () -> Unit,
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

    override fun clear() {
        runningCommands.clear()
    }
}
