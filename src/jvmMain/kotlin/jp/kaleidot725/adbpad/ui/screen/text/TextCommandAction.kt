package jp.kaleidot725.adbpad.ui.screen.text

import jp.kaleidot725.adbpad.core.mvi.MVIAction
import jp.kaleidot725.adbpad.domain.model.command.TextCommand

sealed class TextCommandAction : MVIAction {
    data class UpdateSearchText(
        val text: String,
    ) : TextCommandAction()

    data object AddNewText : TextCommandAction()

    data class UpdateInputText(
        val text: String,
    ) : TextCommandAction()

    data class SendTextCommand(
        val command: TextCommand,
    ) : TextCommandAction()

    data object SendTabCommand : TextCommandAction()

    data class DeleteInputText(
        val command: TextCommand,
    ) : TextCommandAction()

    data object NextCommand : TextCommandAction()

    data class SelectCommand(
        val command: TextCommand,
    ) : TextCommandAction()

    data object PreviousCommand : TextCommandAction()
}
