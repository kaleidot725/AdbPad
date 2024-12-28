package jp.kaleidot725.adbpad.domain.service

import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.domain.model.os.OSContext
import jp.kaleidot725.adbpad.domain.model.setting.Appearance
import jp.kaleidot725.adbpad.domain.model.setting.SdkPath
import jp.kaleidot725.adbpad.domain.model.setting.WindowSize
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.io.IOException

object SettingFileCreator {
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

    @Serializable
    data class Setting(
        val language: Language.Type = Language.Type.ENGLISH,
        val appearance: Appearance = Appearance.LIGHT,
        val sdkPath: SdkPath = SdkPath(),
        val inputTexts: List<String> = emptyList(),
        val windowSize: WindowSize = WindowSize.DEFAULT,
    )
}
