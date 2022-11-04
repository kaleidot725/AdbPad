package jp.kaleidot725.adbpad.domain.service

import jp.kaleidot725.adbpad.domain.model.InputTextCommand

object InputTextCommandFactory {
    fun createNew(text: String): InputTextCommand {
        return InputTextCommand(text = text, isRunning = false)
    }
}