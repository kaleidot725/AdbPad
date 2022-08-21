package jp.kaleidot725.adbpad.model.usecase

import jp.kaleidot725.adbpad.model.data.InputText
import jp.kaleidot725.adbpad.model.data.Setting

class GetInputTextUseCase {
    operator fun invoke(): List<InputText> {
        val setting = Setting.load()
        return setting?.inputTexts ?: emptyList()
    }
}
