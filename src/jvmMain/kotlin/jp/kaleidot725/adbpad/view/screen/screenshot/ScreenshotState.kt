package jp.kaleidot725.adbpad.view.screen.screenshot

import jp.kaleidot725.adbpad.domain.model.Device
import jp.kaleidot725.adbpad.domain.model.ScreenshotPreview
import jp.kaleidot725.adbpad.domain.model.command.ScreenshotCommand

data class ScreenshotState(
    val preview: ScreenshotPreview = ScreenshotPreview(emptyList()),
    val commands: List<ScreenshotCommand> = emptyList(),
    val selectedDevice: Device? = null,
    val isCapturing: Boolean = false
)
