package jp.kaleidot725.adbpad.domain.model.command

import com.malinskiy.adam.request.shell.v1.ShellCommandRequest

data class TextCommand(
    val text: String,
    val isRunning: Boolean = false,
) {
    val requests: List<ShellCommandRequest> = listOf(ShellCommandRequest("input text $text"))
}