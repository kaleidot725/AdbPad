package jp.kaleidot725.adbpad.domain.model.command

import java.time.LocalDateTime

data class CommandExecutionHistory(
    val commandName: String,
    val commandStrings: List<String>,
    val output: String,
    val exitCode: Int,
    val timestamp: LocalDateTime,
    val isSuccess: Boolean,
) {
    val formattedTimestamp: String
        get() = timestamp.toString()
}
