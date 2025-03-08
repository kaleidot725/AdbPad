package jp.kaleidot725.adbpad.ui.screen.command

import jp.kaleidot725.adbpad.core.mvi.MVIAction
import jp.kaleidot725.adbpad.domain.model.command.NormalCommand
import jp.kaleidot725.adbpad.domain.model.command.NormalCommandCategory

sealed class CommandAction : MVIAction {
    data class ExecuteCommand(
        val command: NormalCommand,
    ) : CommandAction()

    data class ClickCategoryTab(
        val category: NormalCommandCategory,
    ) : CommandAction()
}
