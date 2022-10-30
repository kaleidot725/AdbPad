package jp.kaleidot725.adbpad.view.screen.menu

import jp.kaleidot725.adbpad.domain.model.Device
import jp.kaleidot725.adbpad.domain.model.Menu

data class MenuState(
    val menus: List<Menu> = emptyList(),
    val selectedMenu: Menu? = null,
    val devices: List<Device> = emptyList(),
    val selectedDevice: Device? = null
)
