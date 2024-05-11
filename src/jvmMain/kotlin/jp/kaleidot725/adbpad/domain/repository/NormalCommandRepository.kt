package jp.kaleidot725.adbpad.domain.repository

import jp.kaleidot725.adbpad.domain.model.command.NormalCommand
import jp.kaleidot725.adbpad.domain.model.device.Device

interface NormalCommandRepository {
    fun getCommands(): List<NormalCommand>

    suspend fun sendCommand(
        device: Device,
        command: NormalCommand,
        onStart: suspend () -> Unit,
        onComplete: suspend () -> Unit,
        onFailed: suspend () -> Unit,
    )
}
