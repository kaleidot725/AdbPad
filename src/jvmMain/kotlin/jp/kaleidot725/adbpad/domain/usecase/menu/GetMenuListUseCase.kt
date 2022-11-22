package jp.kaleidot725.adbpad.domain.usecase.menu

import jp.kaleidot725.adbpad.view.model.Menu

class GetMenuListUseCase {
    operator fun invoke(): List<Menu> {
        return listOf(
            Menu.Command,
            Menu.InputText,
            Menu.Screenshot
        )
    }
}
