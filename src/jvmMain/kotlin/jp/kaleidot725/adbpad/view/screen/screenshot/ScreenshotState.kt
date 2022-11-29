package jp.kaleidot725.adbpad.view.screen.screenshot

import jp.kaleidot725.adbpad.domain.model.command.ScreenshotCommand
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.model.screenshot.ScreenshotPreview

data class ScreenshotState(
    val preview: ScreenshotPreview = ScreenshotPreview(emptyList()),
    val commands: List<ScreenshotCommand> = emptyList(),
    val selectedDevice: Device? = null,
    val isCapturing: Boolean = false
) {
    val canExecute: Boolean = selectedDevice != null
}
