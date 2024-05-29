package jp.kaleidot725.adbpad.domain.repository

import jp.kaleidot725.adbpad.domain.model.device.Device

interface KeyCommandRepository {
    suspend fun sendKeyCommand(
        device: Device,
        keycode: Int,
        onStart: suspend () -> Unit,
        onComplete: suspend () -> Unit,
        onFailed: suspend () -> Unit,
    )
}
