package jp.kaleidot725.adbpad.view.screen.command

import jp.kaleidot725.adbpad.domain.model.Command
import jp.kaleidot725.adbpad.domain.model.Device

data class CommandState(
    val commands: List<Command> = emptyList(),
    val selectedDevice: Device? = null,
) {
    val canExecuteCommand: Boolean get() = selectedDevice != null
}
