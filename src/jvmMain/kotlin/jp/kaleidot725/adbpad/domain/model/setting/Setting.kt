package jp.kaleidot725.adbpad.domain.model.setting

import kotlinx.serialization.Serializable

@Serializable
data class Setting(
    val sdkPath: SdkPath = SdkPath(),
    val inputTexts: List<String> = emptyList(),
    val windowSize: WindowSize = WindowSize.DEFAULT
)
