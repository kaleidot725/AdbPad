package jp.kaleidot725.adbpad.domain.model.command

import jp.kaleidot725.adbpad.domain.model.Language

interface ScreenshotCommand {
    val title: String
    val isRunning: Boolean

    data class Dark(override val isRunning: Boolean) : ScreenshotCommand {
        override val title: String get() = Language.SCREENSHOT_TAKE_BY_DARK_THEME
    }

    data class Light(override val isRunning: Boolean) : ScreenshotCommand {
        override val title: String get() = Language.SCREENSHOT_TAKE_BY_LIGHT_THEME
    }

    data class Current(override val isRunning: Boolean) : ScreenshotCommand {
        override val title: String get() = Language.SCREENSHOT_TAKE_BY_CURRENT_THEME
    }

    data class Both(override val isRunning: Boolean) : ScreenshotCommand {
        override val title: String get() = Language.SCREENSHOT_TAKE_BY_BOTH_THEME
    }
}
