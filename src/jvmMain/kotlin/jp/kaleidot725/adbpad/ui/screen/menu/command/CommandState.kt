package jp.kaleidot725.adbpad.ui.screen.menu.command

import jp.kaleidot725.adbpad.domain.model.command.NormalCommand
import jp.kaleidot725.adbpad.domain.model.command.NormalCommandCategory
import jp.kaleidot725.adbpad.domain.model.command.NormalCommandGroup
import jp.kaleidot725.adbpad.domain.model.device.Device

data class CommandState(
    val commands: NormalCommandGroup = NormalCommandGroup.Empty,
    val filtered: NormalCommandCategory? = null,
    val selectedDevice: Device? = null,
) {
    val canExecuteCommand: Boolean get() = selectedDevice != null
}
