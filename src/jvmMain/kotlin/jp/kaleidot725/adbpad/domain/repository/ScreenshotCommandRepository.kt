package jp.kaleidot725.adbpad.domain.repository

import jp.kaleidot725.adbpad.domain.model.Device
import jp.kaleidot725.adbpad.domain.model.ScreenshotPreview
import jp.kaleidot725.adbpad.domain.model.command.ScreenshotCommand

interface ScreenshotCommandRepository {
    fun getCommands(): List<ScreenshotCommand>

    fun getPreview(): ScreenshotPreview

    suspend fun sendCommand(
        device: Device,
        command: ScreenshotCommand,
        onStart: suspend () -> Unit,
        onComplete: suspend () -> Unit,
        onFailed: suspend () -> Unit
    )
}
