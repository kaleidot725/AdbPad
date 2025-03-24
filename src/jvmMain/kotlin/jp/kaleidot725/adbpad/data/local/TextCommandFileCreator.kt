package jp.kaleidot725.adbpad.data.local

import jp.kaleidot725.adbpad.domain.model.command.TextCommand
import jp.kaleidot725.adbpad.domain.model.os.OSContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.io.File
import java.io.IOException

object TextCommandFileCreator {
    fun save(setting: TextCommandSetting): Boolean =
        try {
            createDir()
            File(getFilePath()).outputStream().apply {
                this.write(Json.encodeToString(setting).toByteArray())
                this.close()
            }
            true
        } catch (exception: IOException) {
            false
        }

    fun load(): TextCommandSetting =
        try {
            val content = File(getFilePath()).readText()
            Json.decodeFromString(string = content)
        } catch (e: Exception) {
            TextCommandSetting()
        }

    private fun getDirPath() = OSContext.resolveOSContext().directory

    private fun getFilePath() = getDirPath() + "text_command.json"

    private fun createDir() {
        try {
            val file = File(getDirPath())
            if (!file.exists()) file.mkdir()
        } catch (e: Exception) {
            return
        }
    }

    @Serializable
    data class TextCommandSetting(
        val values: List<TextCommand> = emptyList(),
    )
}
