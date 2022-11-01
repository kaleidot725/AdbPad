package jp.kaleidot725.adbpad.domain.model

sealed class Event(val message: String, val level: Level) {
    data class StartCommand(
        val commandName: String
    ) : Event(message = String.format(Language.COMMAND_START_EVENT_FORMAT, commandName), Level.INFO)

    data class EndCommand(
        val commandName: String
    ) : Event(message = String.format(Language.COMMAND_END_EVENT_FORMAT, commandName), Level.INFO)

    data class ErrorCommand(
        val commandName: String
    ) : Event(message = String.format(Language.COMMAND_ERROR_EVENT_FORMAT, commandName), Level.ERROR)

    object NULL : Event("", Level.INFO)

    enum class Level {
        INFO,
        WARN,
        ERROR
    }
}
