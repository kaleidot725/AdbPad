package jp.kaleidot725.adbpad.ui.screen.menu

import jp.kaleidot725.adbpad.domain.model.Menu
import jp.kaleidot725.adbpad.domain.usecase.menu.GetMenuListUseCase
import jp.kaleidot725.adbpad.ui.common.ChildStateHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class MenuStateHolder(
    private val getMenuListUseCase: GetMenuListUseCase,
) : ChildStateHolder<MenuState> {
    private val coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main + Dispatchers.IO)

    private val menus: MutableStateFlow<List<Menu>> = MutableStateFlow(emptyList())
    private val selectedMenu: MutableStateFlow<Menu> = MutableStateFlow(Menu.Command)

    override val state: StateFlow<MenuState> =
        combine(menus, selectedMenu) { menus, selectedMenu ->
            MenuState(menus, selectedMenu)
        }.stateIn(coroutineScope, SharingStarted.WhileSubscribed(), MenuState())

    override fun setup() {
        menus.value = getMenuListUseCase()
    }

    override fun refresh() { }

    override fun dispose() {
        coroutineScope.cancel()
    }

    fun selectMenu(menu: Menu) {
        selectedMenu.value = menu
    }
}
