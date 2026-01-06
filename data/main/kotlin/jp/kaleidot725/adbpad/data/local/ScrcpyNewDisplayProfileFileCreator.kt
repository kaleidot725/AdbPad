package jp.kaleidot725.adbpad.data.local

import jp.kaleidot725.adbpad.domain.model.scrcpy.ScrcpyNewDisplayProfile
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

class ScrcpyNewDisplayProfileFileCreator {
    private val file = File("scrcpy_profiles.json")
    private val json =
        Json {
            ignoreUnknownKeys = true
            prettyPrint = true
        }

    fun load(): List<ScrcpyNewDisplayProfile> {
        if (!file.exists()) return emptyList()
        return try {
            json.decodeFromString(file.readText())
        } catch (e: Exception) {
            emptyList()
        }
    }

    fun save(profiles: List<ScrcpyNewDisplayProfile>) {
        try {
            file.parentFile?.let { parent ->
                if (!parent.exists()) {
                    parent.mkdirs()
                }
            }
            file.writeText(json.encodeToString(profiles))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
