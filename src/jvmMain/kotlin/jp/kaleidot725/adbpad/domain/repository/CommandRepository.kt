package jp.kaleidot725.adbpad.domain.repository

import jp.kaleidot725.adbpad.domain.model.Command
import jp.kaleidot725.adbpad.domain.model.Device

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