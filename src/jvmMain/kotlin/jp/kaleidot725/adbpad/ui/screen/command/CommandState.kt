package jp.kaleidot725.adbpad.ui.screen.command

import jp.kaleidot725.adbpad.domain.model.command.NormalCommandCategory
import jp.kaleidot725.adbpad.domain.model.command.NormalCommandGroup
import jp.kaleidot725.adbpad.domain.model.device.Device

data class CommandState(
    val commands: NormalCommandGroup = NormalCommandGroup.Empty,
    val filtered: NormalCommandCategory = NormalCommandCategory.ALL,
    val selectedDevice: Device? = null,
) {
    val canExecuteCommand: Boolean get() = selectedDevice != null
}
