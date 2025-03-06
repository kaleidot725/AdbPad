package jp.kaleidot725.adbpad.ui.common.dummy

import jp.kaleidot725.adbpad.domain.model.command.TextCommand

object TextCommandDummy {
    val value =
        TextCommand(
            title = "TITLE",
            text = "TEXT",
        )

    val values =
        listOf(
            TextCommand(
                title = "TITLE",
                text = "TEXT",
            ),
            TextCommand(
                title = "TITLE",
                text = "TEXT",
            ),
            TextCommand(
                title = "TITLE",
                text = "TEXT",
            ),
        )
}
