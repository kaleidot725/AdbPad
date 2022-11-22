package jp.kaleidot725.adbpad.view.screen.command

import jp.kaleidot725.adbpad.domain.model.command.NormalCommand
import jp.kaleidot725.adbpad.domain.model.device.Device

data class CommandState(
    val commands: List<NormalCommand> = emptyList(),
    val selectedDevice: Device? = null,
) {
    val canExecuteCommand: Boolean get() = selectedDevice != null
}
