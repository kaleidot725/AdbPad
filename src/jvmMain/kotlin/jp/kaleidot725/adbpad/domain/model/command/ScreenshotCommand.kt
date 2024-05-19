package jp.kaleidot725.adbpad.domain.model.command

import jp.kaleidot725.adbpad.domain.model.language.Language

interface ScreenshotCommand {
    val title: String
    val isRunning: Boolean

    data class Dark(override val isRunning: Boolean) : ScreenshotCommand {
        override val title: String get() = Language.screenshotTakeByDarkTheme
    }

    data class Light(override val isRunning: Boolean) : ScreenshotCommand {
        override val title: String get() = Language.screenshotTakeByLightTheme
    }

    data class Current(override val isRunning: Boolean) : ScreenshotCommand {
        override val title: String get() = Language.screenshotTakeByCurrentTheme
    }

    data class Both(override val isRunning: Boolean) : ScreenshotCommand {
        override val title: String get() = Language.screenshotTakeByBothTheme
    }
}
