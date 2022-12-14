package jp.kaleidot725.adbpad.view.screen.menu

import jp.kaleidot725.adbpad.domain.model.Menu
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.usecase.device.GetDevicesFlowUseCase
import jp.kaleidot725.adbpad.domain.usecase.device.GetSelectedDeviceFlowUseCase
import jp.kaleidot725.adbpad.domain.usecase.device.SelectDeviceUseCase
import jp.kaleidot725.adbpad.domain.usecase.menu.GetMenuListUseCase
import jp.kaleidot725.adbpad.view.common.ChildStateHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MenuStateHolder(
    private val getAndroidDevicesFlowUseCase: GetDevicesFlowUseCase,
    private val getMenuListUseCase: GetMenuListUseCase,
    private val getSelectedDeviceFlowUseCase: GetSelectedDeviceFlowUseCase,
    private val selectDeviceUseCase: SelectDeviceUseCase
) : ChildStateHolder<MenuState> {
    private val coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main + Dispatchers.IO)

    private val menus: MutableStateFlow<List<Menu>> = MutableStateFlow(emptyList())
    private val selectedMenu: MutableStateFlow<Menu?> = MutableStateFlow(null)

    private val devices: StateFlow<List<Device>> = getAndroidDevicesFlowUseCase()
        .stateIn(coroutineScope, SharingStarted.WhileSubscribed(), emptyList())

    private val selectedDevice: StateFlow<Device?> = getSelectedDeviceFlowUseCase()
        .stateIn(coroutineScope, SharingStarted.WhileSubscribed(), null)

    override val state: StateFlow<MenuState> = combine(
        menus, selectedMenu, devices, selectedDevice
    ) { menus, selectedMenu, devices, selectedDevice ->
        MenuState(menus, selectedMenu, devices, selectedDevice)
    }.stateIn(coroutineScope, SharingStarted.WhileSubscribed(), MenuState())

    override fun setup() {
        menus.value = getMenuListUseCase()
        selectedMenu.value = menus.value.firstOrNull()
    }

    override fun dispose() {
        coroutineScope.cancel()
    }

    fun selectDevice(device: Device) {
        coroutineScope.launch {
            selectDeviceUseCase(device)
        }
    }

    fun selectMenu(menu: Menu) {
        selectedMenu.value = menu
    }
}
