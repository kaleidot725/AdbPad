package jp.kaleidot725.adbpad.ui.screen.menu

import jp.kaleidot725.adbpad.domain.model.Menu

data class MenuState(
    val menus: List<Menu> = emptyList(),
    val selectedMenu: Menu = Menu.Command,
)
