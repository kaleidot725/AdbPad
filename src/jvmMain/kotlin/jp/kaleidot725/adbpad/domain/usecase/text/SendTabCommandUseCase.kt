package jp.kaleidot725.adbpad.domain.usecase.text

import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.repository.KeyCommandRepository

class SendTabCommandUseCase(
    private val keyCommandRepository: KeyCommandRepository,
) {
    suspend operator fun invoke(
        device: Device,
        onStart: suspend () -> Unit,
        onFailed: suspend () -> Unit,
        onComplete: suspend () -> Unit,
    ) {
        val tabKeyCode = 61
        keyCommandRepository.sendKeyCommand(
            device = device,
            keycode = tabKeyCode,
            onStart = {
                onStart()
            },
            onFailed = {
                onFailed()
            },
            onComplete = {
                onComplete()
            },
        )
    }
}
