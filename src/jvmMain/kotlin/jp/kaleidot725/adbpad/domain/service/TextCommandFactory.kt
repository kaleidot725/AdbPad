package jp.kaleidot725.adbpad.domain.service

import jp.kaleidot725.adbpad.domain.model.command.TextCommand

object TextCommandFactory {
    fun createNew(
        title: String,
        text: String,
    ): TextCommand = TextCommand(title = title, text = text, isRunning = false)
}
