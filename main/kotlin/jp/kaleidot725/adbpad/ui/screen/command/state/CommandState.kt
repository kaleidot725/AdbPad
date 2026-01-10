package jp.kaleidot725.adbpad.ui.screen.command.state

import jp.kaleidot725.adbpad.core.mvi.MVIState
import jp.kaleidot725.adbpad.domain.model.command.CommandExecutionHistory
import jp.kaleidot725.adbpad.domain.model.command.NormalCommandCategory
import jp.kaleidot725.adbpad.domain.model.command.NormalCommandGroup
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.ui.screen.command.model.CommandLayoutMode

data class CommandState(
    val commands: NormalCommandGroup = NormalCommandGroup.Empty,
    val filtered: NormalCommandCategory = NormalCommandCategory.ALL,
    val selectedDevice: Device? = null,
    val layoutMode: CommandLayoutMode = CommandLayoutMode.CARD,
    val executionHistory: CommandExecutionHistory? = null,
) : MVIState {
    val canExecuteCommand: Boolean get() = selectedDevice != null
}
