package jp.kaleidot725.adbpad.model.usecase

import jp.kaleidot725.adbpad.model.data.InputText

class GetInputTextUseCase {
    operator fun invoke(): List<InputText> {
        return listOf(
            InputText("ID入力", "aiueo"),
            InputText("ID入力", "cdefg"),
            InputText("ID入力", "12345"),
            InputText("ID入力", "67890"),
            InputText("ID入力", "09876"),
            InputText("ID入力", "543211"),
            InputText("ID入力", "日本語入力"),
        )
    }
}
