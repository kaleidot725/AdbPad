package jp.kaleidot725.adbpad.model.utils

import jp.kaleidot725.adbpad.model.data.OSContext
import jp.kaleidot725.adbpad.model.data.Setting
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.io.IOException

object SettingUtils {
    fun save(setting: Setting): Boolean {
        return try {
            createDir()
            File(getFilePath()).outputStream().apply {
                this.write(Json.encodeToString(setting).toByteArray())
                this.close()
            }
            true
        } catch (exception: IOException) {
            false
        }
    }

    fun load(): Setting {
        return try {
            val content = File(getFilePath()).readText()
            Json.decodeFromString(string = content)
        } catch (e: Exception) {
            Setting()
        }
    }

    private fun getDirPath() = OSContext.resolveOSContext().directory
    private fun getFilePath() = getDirPath() + "config.json"

    private fun createDir() {
        try {
            val file = File(getDirPath())
            if (!file.exists()) file.mkdir()
        } catch (e: Exception) {
            return
        }
    }
}
