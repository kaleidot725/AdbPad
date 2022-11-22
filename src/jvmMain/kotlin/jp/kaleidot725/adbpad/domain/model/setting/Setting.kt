package jp.kaleidot725.adbpad.domain.model.setting

import jp.kaleidot725.adbpad.view.model.WindowSize
import kotlinx.serialization.Serializable

@Serializable
data class Setting(
    val adbPath: String? = null,
    val androidSdkPath: String? = null,
    val adbServerPort: Int? = 20010,
    val inputTexts: List<String> = emptyList(),
    val windowSize: WindowSize = WindowSize.DEFAULT
)
