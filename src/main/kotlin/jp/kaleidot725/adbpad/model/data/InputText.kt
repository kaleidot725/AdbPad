package jp.kaleidot725.adbpad.model.data

import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class InputText(
    val content: String,
    private val uuid: String = UUID.randomUUID().toString()
)
