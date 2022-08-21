package jp.kaleidot725.adbpad.model.usecase

import jp.kaleidot725.adbpad.model.data.InputText
import jp.kaleidot725.adbpad.model.data.Setting

class DeleteInputTextUseCase {
    operator fun invoke(inputText: InputText): Boolean {
        val oldSetting = Setting.load() ?: Setting()
        val newInputTexts = oldSetting.inputTexts.toMutableList().apply { remove(inputText) }
        val newSetting = oldSetting.copy(inputTexts = newInputTexts)
        return Setting.write(newSetting)
    }
}