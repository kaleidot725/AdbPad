package jp.kaleidot725.adbpad.domain.model.command

data class CommandExecutionHistory(
    val commandStrings: List<String>,
    val output: String,
)
