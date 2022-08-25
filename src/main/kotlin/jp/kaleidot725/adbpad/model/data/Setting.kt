package jp.kaleidot725.adbpad.model.data

import kotlinx.serialization.Serializable

@Serializable
data class Setting(
    val inputTexts: List<String> = emptyList()
)
