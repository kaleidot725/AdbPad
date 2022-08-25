package jp.kaleidot725.adbpad.model.data

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.io.IOException

@Serializable
data class Setting(
    val inputTexts: List<String> = emptyList()
) {
    companion object {
        fun write(setting: Setting): Boolean {
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

        fun load(): Setting? {
            return try {
                val content = File(getFilePath()).readText()
                Json.decodeFromString(string = content)
            } catch (e: Exception) {
                null
            }
        }

        private fun createDir() {
            try {
                val file = File(getDirPath())
                if (!file.exists()) file.mkdir()
            } catch (e: Exception) {
                return
            }
        }

        private fun getDirPath() = OperatingSystem.resolveOperationSystem().direcotry
        private fun getFilePath() = getDirPath() + "config.json"
    }
}
