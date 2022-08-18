package jp.kaleidot725.adbpad

import jp.kaleidot725.adbpad.model.Menu
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainStateHolder {
    private val _state: MutableStateFlow<MainState> = MutableStateFlow(MainState())
    val state: StateFlow<MainState> = _state

    fun selectDevice(device: String) {
        _state.value = _state.value.copy(selectedDevice = device)
    }

    fun selectMenu(menu: Menu) {
        _state.value = _state.value.copy(selectedMenu = menu)
    }

    fun executeCommand() {

    }

    fun executeAutoFillText() {

    }

    fun takeScreenShot() {

    }

    fun takeThemeScreenShot() {

    }
}

