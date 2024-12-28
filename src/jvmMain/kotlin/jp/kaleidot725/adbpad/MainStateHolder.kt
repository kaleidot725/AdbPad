package jp.kaleidot725.adbpad

import jp.kaleidot725.adbpad.domain.model.Dialog
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.domain.model.setting.WindowSize
import jp.kaleidot725.adbpad.domain.usecase.adb.StartAdbUseCase
import jp.kaleidot725.adbpad.domain.usecase.device.GetSelectedDeviceFlowUseCase
import jp.kaleidot725.adbpad.domain.usecase.device.SelectDeviceUseCase
import jp.kaleidot725.adbpad.domain.usecase.device.UpdateDevicesUseCase
import jp.kaleidot725.adbpad.domain.usecase.language.GetLanguageUseCase
import jp.kaleidot725.adbpad.domain.usecase.refresh.RefreshUseCase
import jp.kaleidot725.adbpad.domain.usecase.theme.GetDarkModeFlowUseCase
import jp.kaleidot725.adbpad.domain.usecase.window.GetWindowSizeUseCase
import jp.kaleidot725.adbpad.domain.usecase.window.SaveWindowSizeUseCase
import jp.kaleidot725.adbpad.ui.common.ChildStateHolder
import jp.kaleidot725.adbpad.ui.common.ParentStateHolder
import jp.kaleidot725.adbpad.ui.screen.command.CommandStateHolder
import jp.kaleidot725.adbpad.ui.screen.screenshot.ScreenshotStateHolder
import jp.kaleidot725.adbpad.ui.screen.text.TextCommandStateHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class MainStateHolder(
    val commandStateHolder: CommandStateHolder,
    val textCommandStateHolder: TextCommandStateHolder,
    val screenshotStateHolder: ScreenshotStateHolder,
    private val getWindowSizeUseCase: GetWindowSizeUseCase,
    private val saveWindowSizeUseCase: SaveWindowSizeUseCase,
    private val startAdbUseCase: StartAdbUseCase,
    private val getDarkModeFlowUseCase: GetDarkModeFlowUseCase,
    private val getLanguageUseCase: GetLanguageUseCase,
    private val refreshUseCase: RefreshUseCase,
    private val updateDevicesUseCase: UpdateDevicesUseCase,
    private val getSelectedDeviceFlowUseCase: GetSelectedDeviceFlowUseCase,
    private val selectDeviceUseCase: SelectDeviceUseCase,
) : ParentStateHolder {
    private val coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main + Dispatchers.IO)
    private val language: MutableStateFlow<Language.Type> = MutableStateFlow(Language.Type.ENGLISH)
    private val windowSize: MutableStateFlow<WindowSize> = MutableStateFlow(WindowSize.UNKNOWN)
    private val isDark: MutableStateFlow<Boolean?> = MutableStateFlow(null)
    private val dialog: MutableStateFlow<Dialog?> = MutableStateFlow(null)
    private val category: MutableStateFlow<MainCategory> = MutableStateFlow(MainCategory.Command)

    private var deviceJob: Job? = null
    private val _devices: MutableStateFlow<List<Device>> = MutableStateFlow(emptyList())
    private val devices: StateFlow<List<Device>> = _devices.asStateFlow()

    private var selectedDeviceJob: Job? = null
    private val _selectedDevice: MutableStateFlow<Device?> = MutableStateFlow(null)
    private val selectedDevice: StateFlow<Device?> = _selectedDevice.asStateFlow()

    val state: StateFlow<MainState> =
        combine(language, isDark, windowSize, dialog, category, devices, selectedDevice) { data ->
            MainState(
                data[0] as Language.Type,
                data[1] as Boolean?,
                data[2] as WindowSize,
                data[3] as Dialog?,
                data[4] as MainCategory,
                data[5] as List<Device>,
                data[6] as Device?,
            )
        }.stateIn(coroutineScope, SharingStarted.WhileSubscribed(), MainState())

    private val children: List<ChildStateHolder<*>> =
        listOf(
            commandStateHolder,
            textCommandStateHolder,
            screenshotStateHolder,
        )

    init {
        startSyncDarkMode()
        restoreWindowSize()
        checkAdbServer()
        syncLanguage()
        collectDevices()
    }

    override fun setup() {
        children.forEach { it.setup() }
    }

    override fun refresh() {
        startSyncDarkMode()
        checkAdbServer()
        syncLanguage()
        collectDevices()
        refreshUseCase()
        children.forEach { it.refresh() }
    }

    override fun dispose() {
        children.forEach { it.dispose() }
    }

    fun saveSetting(windowSize: WindowSize) {
        saveWindowSize(windowSize)
    }

    fun openSetting() {
        dialog.value = Dialog.Setting
    }

    fun clickCategory(category: MainCategory) {
        this.category.value = category
    }

    fun selectDevice(device: Device) {
        coroutineScope.launch {
            selectDeviceUseCase(device)
        }
    }

    private var themeFlowJob: Job? = null

    private fun startSyncDarkMode() {
        themeFlowJob?.cancel()
        themeFlowJob =
            coroutineScope.launch {
                val flow = getDarkModeFlowUseCase()
                flow.collectLatest {
                    isDark.value = it
                }
            }
    }

    private fun saveWindowSize(windowSize: WindowSize) {
        coroutineScope.launch {
            saveWindowSizeUseCase(windowSize)
        }
    }

    private fun restoreWindowSize() {
        coroutineScope.launch {
            windowSize.value = getWindowSizeUseCase()
        }
    }

    private fun checkAdbServer() {
        coroutineScope.launch {
            dialog.value = if (startAdbUseCase()) null else Dialog.AdbError
        }
    }

    private fun syncLanguage() {
        coroutineScope.launch {
            val type = getLanguageUseCase()
            Language.switch(type)
            language.value = type
        }
    }

    private fun collectDevices() {
        deviceJob?.cancel()
        deviceJob =
            coroutineScope.launch {
                while (isActive) {
                    _devices.value = updateDevicesUseCase()
                    delay(1000)
                }
            }

        selectedDeviceJob?.cancel()
        selectedDeviceJob =
            coroutineScope.launch {
                getSelectedDeviceFlowUseCase().collect {
                    _selectedDevice.value = it
                }
            }
    }
}
