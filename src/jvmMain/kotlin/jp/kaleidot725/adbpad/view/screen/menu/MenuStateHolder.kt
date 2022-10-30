package jp.kaleidot725.adbpad.view.screen.menu

import com.malinskiy.adam.request.device.Device
import jp.kaleidot725.adbpad.domain.model.Event
import jp.kaleidot725.adbpad.domain.model.Menu
import jp.kaleidot725.adbpad.domain.usecase.adb.StartAdbUseCase
import jp.kaleidot725.adbpad.domain.usecase.device.GetDevicesFlowUseCase
import jp.kaleidot725.adbpad.domain.usecase.menu.GetMenuListUseCase
import jp.kaleidot725.adbpad.view.common.ChildStateHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MenuStateHolder(
    val startAdbUseCase: StartAdbUseCase,
    val getAndroidDevicesFlowUseCase: GetDevicesFlowUseCase,
    val getMenuListUseCase: GetMenuListUseCase
) : ChildStateHolder<MenuState> {
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

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()
    override val event: SharedFlow<Event> = _event

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
