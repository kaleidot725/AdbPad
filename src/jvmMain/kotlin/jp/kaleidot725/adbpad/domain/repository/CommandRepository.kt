package jp.kaleidot725.adbpad.domain.repository

import jp.kaleidot725.adbpad.domain.model.Device
import jp.kaleidot725.adbpad.domain.model.command.Command

interface CommandRepository {
    fun getCommands(): List<Command>

    suspend fun sendCommand(
        device: Device,
        command: Command,
        onStart: suspend () -> Unit,
        onComplete: suspend () -> Unit,
        onFailed: suspend () -> Unit
    )
}
