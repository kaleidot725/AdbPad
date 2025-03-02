package jp.kaleidot725.adbpad.domain.model.command

import com.malinskiy.adam.request.shell.v1.ShellCommandRequest
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class TextCommand(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val text: String,
    val isRunning: Boolean = false,
) {
    val requests: List<ShellCommandRequest> get() = listOf(ShellCommandRequest("input text $text"))

    enum class Option {
        SendWithTab,
        SendWithNewLine,
    }
}
