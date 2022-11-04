package jp.kaleidot725.adbpad.domain.model

import com.malinskiy.adam.request.shell.v1.ShellCommandRequest

data class InputTextCommand(
    val text: String,
    val isRunning: Boolean = false,
) {
    val requests: List<ShellCommandRequest> = listOf(ShellCommandRequest("input text $text"))
}