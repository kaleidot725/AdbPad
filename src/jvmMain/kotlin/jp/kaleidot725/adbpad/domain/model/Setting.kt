package jp.kaleidot725.adbpad.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Setting(
    val adbPath: String? = null,
    val androidSdkPath: String? = null,
    val adbServerPort: Int? = 20010,
    val inputTexts: List<String> = emptyList(),
    val windowSize: WindowSize = WindowSize.DEFAULT
)
