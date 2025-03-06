package jp.kaleidot725.adbpad.ui.screen.screenshot

import jp.kaleidot725.adbpad.core.mvi.MVIAction
import jp.kaleidot725.adbpad.domain.model.command.ScreenshotCommand
import jp.kaleidot725.adbpad.domain.model.screenshot.Screenshot

sealed class ScreenshotAction : MVIAction {
    data class UpdateSearchText(
        val text: String,
    ) : ScreenshotAction()

    data class TakeScreenshot(
        val command: ScreenshotCommand,
    ) : ScreenshotAction()

    data object OpenDirectory : ScreenshotAction()

    data object CopyScreenshotToClipboard : ScreenshotAction()

    data object DeleteScreenshotToClipboard : ScreenshotAction()

    data class SelectScreenshot(
        val screenshot: Screenshot,
    ) : ScreenshotAction()

    data object NextScreenshot : ScreenshotAction()

    data object PreviousScreenshot : ScreenshotAction()
}
