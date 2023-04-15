package jp.kaleidot725.adbpad.domain.model.log

import jp.kaleidot725.adbpad.domain.model.language.Language

interface Event {
    val message: String
    val level: Level

    data class StartCommand(val commandName: String) : Event {
        override val message: String
            get() = String.format(Language.COMMAND_START_EVENT_FORMAT, commandName)

        override val level: Level
            get() = Level.INFO
    }

    data class EndCommand(val commandName: String) : Event {
        override val message: String
            get() = String.format(Language.COMMAND_END_EVENT_FORMAT, commandName)

        override val level: Level
            get() = Level.INFO
    }

    data class ErrorCommand(val commandName: String) : Event {
        override val message: String
            get() = String.format(Language.COMMAND_ERROR_EVENT_FORMAT, commandName)

        override val level: Level
            get() = Level.ERROR
    }

    data class StartSendTextCommand(val text: String) : Event {
        override val message: String
            get() = String.format(Language.TEXT_COMMAND_START_EVENT_FORMAT, text)

        override val level: Level
            get() = Level.INFO
    }

    data class EndSendTextCommand(val text: String) : Event {
        override val message: String
            get() = String.format(Language.TEXT_COMMAND_END_EVENT_FORMAT, text)

        override val level: Level
            get() = Level.INFO
    }

    data class ErrorSendTextCommand(val text: String) : Event {
        override val message: String
            get() = String.format(Language.TEXT_COMMAND_ERROR_EVENT_FORMAT)

        override val level: Level
            get() = Level.ERROR
    }

    object StartSendScreenshotCommand : Event {
        override val message: String
            get() = Language.SCREENSHOT_COMMAND_START_EVENT_FORMAT

        override val level: Level
            get() = Level.INFO
    }

    object EndSendScreenshotCommand : Event {
        override val message: String
            get() = Language.SCREENSHOT_COMMAND_END_EVENT_FORMAT

        override val level: Level
            get() = Level.INFO
    }

    object ErrorSendScreenshotCommand : Event {
        override val message: String
            get() = Language.SCREENSHOT_COMMAND_ERROR_EVENT_FORMAT

        override val level: Level
            get() = Level.ERROR
    }

    object CopyScreenshotToClipBoard: Event {
        override val message: String
            get() = Language.SCREENSHOT_COPY_TO_CLIPBAORD_EVENT_FORMAT

        override val level: Level
            get() = Level.INFO
    }

    object CantCopyScreenshotToClipBoard: Event {
        override val message: String
            get() = Language.SCREENSHOT_COPY_TO_CLIPBAORD_EVENT_FORMAT

        override val level: Level
            get() = Level.ERROR
    }

    object ClearScreenshotCache: Event {
        override val message: String
            get() = Language.SCREENSHOT_CLEAR_CACHE

        override val level: Level
            get() = Level.INFO
    }

    object NULL : Event {
        override val message: String
            get() = ""

        override val level: Level
            get() = Level.INFO
    }

    enum class Level {
        INFO,
        WARN,
        ERROR
    }
}
