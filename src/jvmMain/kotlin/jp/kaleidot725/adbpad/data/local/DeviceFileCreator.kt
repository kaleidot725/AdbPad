package jp.kaleidot725.adbpad.data.local

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.io.IOException

object DeviceFileCreator {
    private const val FILE_NAME = "device.json"

    fun save(setting: DevicesSetting): Boolean =
        try {
            FilePathUtil.createDir()
            FilePathUtil.getFilePath(FILE_NAME).outputStream().apply {
                this.write(Json.encodeToString(setting).toByteArray())
                this.close()
            }
            true
        } catch (_: IOException) {
            false
        }

    fun load(): DevicesSetting =
        try {
            val content = FilePathUtil.getFilePath(FILE_NAME).readText()
            Json.decodeFromString(string = content)
        } catch (_: Exception) {
            DevicesSetting()
        }

    @Serializable
    data class DevicesSetting(
        val values: List<DeviceSetting> = emptyList(),
    )

    @Serializable
    data class DeviceSetting(
        val serial: String,
        val name: String,
    )
}
