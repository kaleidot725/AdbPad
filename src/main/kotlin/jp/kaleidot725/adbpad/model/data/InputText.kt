package jp.kaleidot725.adbpad.model.data

import kotlinx.serialization.Serializable

@Serializable
data class InputText(
    val content: String,
    private val uuid: String = java.util.UUID.randomUUID().toString()
)
