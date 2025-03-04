package jp.kaleidot725.adbpad.ui.screen.screenshot

import jp.kaleidot725.adbpad.domain.model.command.ScreenshotCommand
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.model.screenshot.Screenshot

data class ScreenshotState(
    val searchText: String = "",
    val preview: Screenshot = Screenshot(null),
    val previews: List<Screenshot> = emptyList(),
    val commands: List<ScreenshotCommand> = emptyList(),
    val selectedDevice: Device? = null,
    val isCapturing: Boolean = false,
) {
    val canExecute: Boolean = selectedDevice != null
}
