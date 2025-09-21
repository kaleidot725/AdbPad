package jp.kaleidot725.adbpad.ui.section.right.state

import jp.kaleidot725.adbpad.core.mvi.MVIAction
import jp.kaleidot725.adbpad.domain.model.command.DeviceControlCommand

sealed class RightAction : MVIAction {
    data class ExecuteCommand(
        val command: DeviceControlCommand,
    ) : RightAction()

    data object LaunchScrcpy : RightAction()

    data object ShowScrcpyControl : RightAction()

    data object HideScrcpyControl : RightAction()
}