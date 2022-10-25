package jp.kaleidot725.adbpad.model.usecase.menu

import jp.kaleidot725.adbpad.model.data.Menu

class GetMenuListUseCase {
    operator fun invoke(): List<Menu> {
        return listOf(
            Menu.Command,
            Menu.InputText,
            Menu.Screenshot
        )
    }
}
