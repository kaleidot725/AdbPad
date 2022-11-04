package jp.kaleidot725.adbpad.domain.service

import jp.kaleidot725.adbpad.domain.model.command.TextCommand

object TextCommandFactory {
    fun createNew(text: String): TextCommand {
        return TextCommand(text = text, isRunning = false)
    }
}