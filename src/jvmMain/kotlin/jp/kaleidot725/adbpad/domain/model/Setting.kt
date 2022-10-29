package jp.kaleidot725.adbpad.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Setting(
    val inputTexts: List<String> = emptyList()
)
