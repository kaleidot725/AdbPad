package jp.kaleidot725.adbpad.domain.model.log

import jp.kaleidot725.adbpad.domain.model.language.Language

interface Event {
    val message: String
    val level: Level

    data class StartCommand(val commandName: String) : Event {
        override val message: String
            get() = String.format(Language.commandStartEventFormat, commandName)

        override val level: Level
            get() = Level.INFO
    }

    data class EndCommand(val commandName: String) : Event {
        override val message: String
            get() = String.format(Language.commandEndEventFormat, commandName)

        override val level: Level
            get() = Level.INFO
    }

    data class ErrorCommand(val commandName: String) : Event {
        override val message: String
            get() = String.format(Language.commandErrorEventFormat, commandName)

        override val level: Level
            get() = Level.ERROR
    }

    data class StartSendTextCommand(val text: String) : Event {
        override val message: String
            get() = String.format(Language.textCommandStartEventFormat, text)

        override val level: Level
            get() = Level.INFO
    }

    data class EndSendTextCommand(val text: String) : Event {
        override val message: String
            get() = String.format(Language.textCommandEndEventFormat, text)

        override val level: Level
            get() = Level.INFO
    }

    data class ErrorSendTextCommand(val text: String) : Event {
        override val message: String
            get() = String.format(Language.textCommandErrorEventFormat)

        override val level: Level
            get() = Level.ERROR
    }

    object StartSendScreenshotCommand : Event {
        override val message: String
            get() = Language.screenshotCommandStartEventFormat

        override val level: Level
            get() = Level.INFO
    }

    object EndSendScreenshotCommand : Event {
        override val message: String
            get() = Language.screenshotCommandEndEventFormat

        override val level: Level
            get() = Level.INFO
    }

    object ErrorSendScreenshotCommand : Event {
        override val message: String
            get() = Language.screenshotCommandErrorEventFormat

        override val level: Level
            get() = Level.ERROR
    }

    object CopyScreenshotToClipBoard : Event {
        override val message: String
            get() = Language.screenshotCopyToClipbaordEventFormat

        override val level: Level
            get() = Level.INFO
    }

    object CantCopyScreenshotToClipBoard : Event {
        override val message: String
            get() = Language.screenshotCopyToClipbaordEventFormat

        override val level: Level
            get() = Level.ERROR
    }

    object ClearScreenshotCache : Event {
        override val message: String
            get() = Language.screenshotClearCache

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
        ERROR,
    }
}
