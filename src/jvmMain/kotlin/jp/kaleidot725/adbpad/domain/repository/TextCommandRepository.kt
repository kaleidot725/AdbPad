package jp.kaleidot725.adbpad.domain.repository

import jp.kaleidot725.adbpad.domain.model.Device
import jp.kaleidot725.adbpad.domain.model.command.TextCommand

interface TextCommandRepository {
    suspend fun getAllTextCommand(): List<TextCommand>

    suspend fun addTextCommand(command: TextCommand): Boolean

    suspend fun removeTextCommand(command: TextCommand): Boolean

    suspend fun sendCommand(
        device: Device,
        command: TextCommand,
        onStart: suspend () -> Unit,
        onComplete: suspend () -> Unit,
        onFailed: suspend () -> Unit
    )

    suspend fun sendUserInputText(
        device: Device,
        text: String,
        onStart: suspend () -> Unit,
        onComplete: suspend () -> Unit,
        onFailed: suspend () -> Unit
    )
}
