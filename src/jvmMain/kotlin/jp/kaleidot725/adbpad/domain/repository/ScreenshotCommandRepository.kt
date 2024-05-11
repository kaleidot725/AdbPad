package jp.kaleidot725.adbpad.domain.repository

import jp.kaleidot725.adbpad.domain.model.command.ScreenshotCommand
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.model.screenshot.Screenshot

interface ScreenshotCommandRepository {
    fun getCommands(): List<ScreenshotCommand>

    suspend fun captureScreenshot(
        device: Device,
        command: ScreenshotCommand,
        onStart: suspend () -> Unit,
        onComplete: suspend (Screenshot) -> Unit,
        onFailed: suspend () -> Unit,
    )

    suspend fun getScreenshotCache(): Screenshot

    suspend fun deleteScreenshotCache()
}
