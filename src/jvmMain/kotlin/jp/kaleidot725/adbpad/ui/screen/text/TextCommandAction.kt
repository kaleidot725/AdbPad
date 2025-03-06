package jp.kaleidot725.adbpad.ui.screen.text

import jp.kaleidot725.adbpad.core.mvi.MVIAction
import jp.kaleidot725.adbpad.domain.model.command.TextCommand

sealed class TextCommandAction : MVIAction {
    data class UpdateSearchText(
        val text: String,
    ) : TextCommandAction()

    data object AddNewText : TextCommandAction()

    data class UpdateCommandTitle(
        val id: String,
        val value: String,
    ) : TextCommandAction()

    data class UpdateCommandText(
        val id: String,
        val value: String,
    ) : TextCommandAction()

    data object SendTextCommand : TextCommandAction()

    data object DeleteSelectedCommandText : TextCommandAction()

    data object NextCommand : TextCommandAction()

    data class SelectCommand(
        val command: TextCommand,
    ) : TextCommandAction()

    data object PreviousCommand : TextCommandAction()

    data class UpdateTextCommandOption(
        val value: TextCommand.Option,
    ) : TextCommandAction()
}
