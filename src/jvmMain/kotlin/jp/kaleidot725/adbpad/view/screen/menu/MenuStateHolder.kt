package jp.kaleidot725.adbpad.view.screen.menu

import com.malinskiy.adam.request.device.Device
import jp.kaleidot725.adbpad.model.data.Menu
import jp.kaleidot725.adbpad.model.usecase.adb.StartAdbUseCase
import jp.kaleidot725.adbpad.model.usecase.device.GetDevicesFlowUseCase
import jp.kaleidot725.adbpad.model.usecase.menu.GetMenuListUseCase
import jp.kaleidot725.adbpad.view.common.StateHolder
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
    val startAdbUseCase: StartAdbUseCase = StartAdbUseCase(),
    val getAndroidDevicesFlowUseCase: GetDevicesFlowUseCase = GetDevicesFlowUseCase(),
    val getMenuListUseCase: GetMenuListUseCase = GetMenuListUseCase(),
) : StateHolder<MenuState> {
    private val coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main + Dispatchers.IO)
    private val menus: MutableStateFlow<List<Menu>> = MutableStateFlow(emptyList())
    private val selectedMenu: MutableStateFlow<Menu?> = MutableStateFlow(null)
    private val devices: MutableStateFlow<List<Device>> = MutableStateFlow(emptyList())
    private val selectedDevice: MutableStateFlow<Device?> = MutableStateFlow(null)
    override val state: StateFlow<MenuState> = combine(
        menus, selectedMenu, devices, selectedDevice
    ) { menus, selectedMenu, devices, selectedDevice ->
        MenuState(menus, selectedMenu, devices, selectedDevice)
    }.stateIn(coroutineScope, SharingStarted.WhileSubscribed(), MenuState())

    override fun setup() {
        menus.value = getMenuListUseCase()
        selectedMenu.value = menus.value.firstOrNull()
        coroutineScope.launch {
            startAdbUseCase()
            getAndroidDevicesFlowUseCase(coroutineScope).collect {
                devices.value = it
                val hasNotDevice = !it.contains(selectedDevice.value)
                if (hasNotDevice) selectedDevice.value = it.firstOrNull()
            }
        }
    }

    override fun dispose() {
        coroutineScope.cancel()
    }

    fun selectDevice(device: Device) {
        selectedDevice.value = device
    }

    fun selectMenu(menu: Menu) {
        selectedMenu.value = menu
    }
}
