package jp.kaleidot725.adbpad.ui.screen.screenshot.state

import jp.kaleidot725.adbpad.core.mvi.MVIState
import jp.kaleidot725.adbpad.domain.model.command.ScreenshotCommand
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.model.sort.SortType
import java.io.File

data class ScreenshotState(
    val searchText: String = "",
    val preview: File? = null,
    val previews: List<File> = emptyList(),
    val selectedCommand: ScreenshotCommand = ScreenshotCommand.Both,
    val commands: List<ScreenshotCommand> = emptyList(),
    val selectedDevice: Device? = null,
    val isCapturing: Boolean = false,
    val sortType: SortType = SortType.SORT_BY_NAME_ASC,
) : MVIState {
    val canExecute: Boolean = selectedDevice != null
}
