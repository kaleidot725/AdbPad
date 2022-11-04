package jp.kaleidot725.adbpad.domain.repository

import jp.kaleidot725.adbpad.domain.model.Device
import jp.kaleidot725.adbpad.domain.model.InputTextCommand

interface InputTextCommandRepository {
    suspend fun getAllTextCommand(): List<InputTextCommand>

    suspend fun addTextCommand(command: InputTextCommand): Boolean

    suspend fun removeTextCommand(command: InputTextCommand): Boolean

    suspend fun sendCommand(
        device: Device,
        command: InputTextCommand,
        onStart: suspend () -> Unit,
        onComplete: suspend () -> Unit,
        onFailed: suspend () -> Unit
    )
}
