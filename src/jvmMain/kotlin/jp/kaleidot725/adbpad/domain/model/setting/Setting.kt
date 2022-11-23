package jp.kaleidot725.adbpad.domain.model.setting

import kotlinx.serialization.Serializable

@Serializable
data class Setting(
    val adbDirectory: String = "",
    val adbServerPort: Int = 30000,
    val androidSdkDirectory: String = "",
    val inputTexts: List<String> = emptyList(),
    val windowSize: WindowSize = WindowSize.DEFAULT
)
