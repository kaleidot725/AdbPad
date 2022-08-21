package jp.kaleidot725.adbpad.model.usecase

import jp.kaleidot725.adbpad.model.data.InputText

class GetInputTextUseCase {
    operator fun invoke(): List<InputText> {
        return listOf(
            InputText("aiueo"),
            InputText("cdefg"),
            InputText("12345"),
            InputText("67890"),
            InputText("09876"),
            InputText("543211"),
            InputText("日本語入力"),
        )
    }
}
