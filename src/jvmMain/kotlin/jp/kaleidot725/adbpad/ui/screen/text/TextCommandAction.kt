package jp.kaleidot725.adbpad.ui.screen.text

import jp.kaleidot725.adbpad.core.mvi.MVIAction
import jp.kaleidot725.adbpad.core.mvi.MVIState
import jp.kaleidot725.adbpad.domain.model.command.TextCommand
import jp.kaleidot725.adbpad.domain.model.device.Device

sealed class TextCommandAction : MVIAction {
    data class UpdateInputText(val text: String) : TextCommandAction()
    data class SendTextCommand(val command: TextCommand) : TextCommandAction()
    data object SendInputText: TextCommandAction()
    data object SendTabCommand: TextCommandAction()
    data object SaveInputText : TextCommandAction()
    data class DeleteInputText(val command: TextCommand): TextCommandAction()
    data object NextCommand : TextCommandAction()
    data class SelectCommand(val command: TextCommand) : TextCommandAction()
    data object PreviousCommand: TextCommandAction()
}
