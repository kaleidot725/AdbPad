package jp.kaleidot725.adbpad.domain.usecase.text

import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.repository.TextCommandRepository

class SendUserInputTextCommandUseCase(
    private val textCommandRepository: TextCommandRepository,
) {
    suspend operator fun invoke(
        device: Device,
        text: String,
        onStart: suspend () -> Unit,
        onFailed: suspend () -> Unit,
        onComplete: suspend () -> Unit,
    ) {
        textCommandRepository.sendUserInputText(
            device = device,
            text = text,
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
