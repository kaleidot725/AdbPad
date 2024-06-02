package jp.kaleidot725.adbpad.ui.screen.menu

import jp.kaleidot725.adbpad.domain.model.Menu
import jp.kaleidot725.adbpad.domain.model.device.Device

data class MenuState(
    val menus: List<Menu> = emptyList(),
    val selectedMenu: Menu? = null,
    val devices: List<Device> = emptyList(),
    val selectedDevice: Device? = null,
)
