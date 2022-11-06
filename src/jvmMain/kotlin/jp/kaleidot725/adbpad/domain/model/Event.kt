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

    data class StartSendTextCommand(
        val text: String
    ) : Event(message = String.format(Language.TEXT_COMMAND_START_EVENT_FORMAT, text), Level.INFO)

    data class EndSendTextCommand(
        val text: String
    ) : Event(message = String.format(Language.TEXT_COMMAND_END_EVENT_FORMAT, text), Level.INFO)

    data class ErrorSendTextCommand(
        val text: String
    ) : Event(message = String.format(Language.TEXT_COMMAND_ERROR_EVENT_FORMAT, text), Level.ERROR)

    object StartSendScreenshotCommand :
        Event(message = Language.SCREENSHOT_COMMAND_START_EVENT_FORMAT, Level.INFO)

    object EndSendScreenshotCommand :
        Event(message = Language.SCREENSHOT_COMMAND_END_EVENT_FORMAT, Level.INFO)

    object ErrorSendScreenshotCommand :
        Event(message = Language.SCREENSHOT_COMMAND_ERROR_EVENT_FORMAT, Level.ERROR)

    object NULL : Event("", Level.INFO)

    enum class Level {
        INFO,
        WARN,
        ERROR
    }
}
