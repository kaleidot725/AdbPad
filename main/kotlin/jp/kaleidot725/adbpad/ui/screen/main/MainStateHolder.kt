package jp.kaleidot725.adbpad.ui.screen.main

import jp.kaleidot725.adbpad.core.mvi.MVIAction
import jp.kaleidot725.adbpad.core.mvi.MVIBase
import jp.kaleidot725.adbpad.core.mvi.MVISideEffect
import jp.kaleidot725.adbpad.core.mvi.MVIState
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.domain.model.setting.WindowSize
import jp.kaleidot725.adbpad.domain.usecase.adb.StartAdbUseCase
import jp.kaleidot725.adbpad.domain.usecase.app.ShutdownAppUseCase
import jp.kaleidot725.adbpad.domain.usecase.appearance.GetAccentColorUseCase
import jp.kaleidot725.adbpad.domain.usecase.language.GetLanguageUseCase
import jp.kaleidot725.adbpad.domain.usecase.refresh.RefreshUseCase
import jp.kaleidot725.adbpad.domain.usecase.theme.GetDarkModeFlowUseCase
import jp.kaleidot725.adbpad.domain.usecase.window.GetWindowSizeUseCase
import jp.kaleidot725.adbpad.domain.usecase.window.SaveWindowSizeUseCase
import jp.kaleidot725.adbpad.ui.screen.command.CommandStateHolder
import jp.kaleidot725.adbpad.ui.screen.device.DeviceSettingsStateHolder
import jp.kaleidot725.adbpad.ui.screen.main.state.MainAction
import jp.kaleidot725.adbpad.ui.screen.main.state.MainCategory
import jp.kaleidot725.adbpad.ui.screen.main.state.MainDialog
import jp.kaleidot725.adbpad.ui.screen.main.state.MainSideEffect
import jp.kaleidot725.adbpad.ui.screen.main.state.MainState
import jp.kaleidot725.adbpad.ui.screen.screenshot.ScreenshotStateHolder
import jp.kaleidot725.adbpad.ui.screen.setting.SettingStateHolder
import jp.kaleidot725.adbpad.ui.screen.text.TextCommandStateHolder
import jp.kaleidot725.adbpad.ui.section.right.RightStateHolder
import jp.kaleidot725.adbpad.ui.section.top.TopStateHolder
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainStateHolder(
    val commandStateHolder: CommandStateHolder,
    val textCommandStateHolder: TextCommandStateHolder,
    val screenshotStateHolder: ScreenshotStateHolder,
    val topStateHolder: TopStateHolder,
    val rightStateHolder: RightStateHolder,
    val deviceSettingsStateHolder: DeviceSettingsStateHolder,
    val settingStateHolder: SettingStateHolder,
    private val getWindowSizeUseCase: GetWindowSizeUseCase,
    private val saveWindowSizeUseCase: SaveWindowSizeUseCase,
    private val startAdbUseCase: StartAdbUseCase,
    private val getDarkModeFlowUseCase: GetDarkModeFlowUseCase,
    private val getLanguageUseCase: GetLanguageUseCase,
    private val getAccentColorUseCase: GetAccentColorUseCase,
    private val refreshUseCase: RefreshUseCase,
    private val shutdownAppUseCase: ShutdownAppUseCase,
) : MVIBase<MainState, MainAction, MainSideEffect>(initialUiState = MainState()) {
    private val children: List<MVIBase<out MVIState, out MVIAction, out MVISideEffect>> =
        listOf(
            commandStateHolder,
            textCommandStateHolder,
            screenshotStateHolder,
            topStateHolder,
            rightStateHolder,
        )

    override fun onSetup() {
        restoreWindowSize()
        startSyncDarkMode()
        checkAdbServer()
        syncLanguage()
        syncAccentColor()
        children.forEach { it.onSetup() }
    }

    override fun onRefresh() {
        startSyncDarkMode()
        checkAdbServer()
        syncLanguage()
        syncAccentColor()
        refreshUseCase()
        children.forEach { it.onRefresh() }
    }

    override fun onAction(uiAction: MainAction) {
        when (uiAction) {
            is MainAction.OpenSetting -> openSetting()
            is MainAction.OpenDeviceSettings -> openDeviceSettings(uiAction.device)
            is MainAction.SaveSetting -> saveSetting(uiAction.windowSize)
            is MainAction.ClickCategory -> clickCategory(uiAction.category)
            is MainAction.ToggleAlwaysOnTop -> toggleAlwaysOnTop()
            is MainAction.Shutdown -> shutdown()
        }
    }

    private fun saveSetting(windowSize: WindowSize) {
        saveWindowSize(windowSize)
    }

    private fun openSetting() {
        update { copy(dialog = MainDialog.Setting) }
    }

    private fun openDeviceSettings(device: Device) {
        update { copy(dialog = MainDialog.DeviceSettings(device)) }
    }

    private fun clickCategory(category: MainCategory) {
        update { copy(category = category) }
    }

    private fun toggleAlwaysOnTop() {
        update { copy(isAlwaysOnTop = !isAlwaysOnTop) }
    }

    private fun shutdown() {
        // Terminate all running Scrcpy processes before app shutdown
        shutdownAppUseCase()

        // Cancel any ongoing coroutines
        themeFlowJob?.cancel()
    }

    private var themeFlowJob: Job? = null

    private fun startSyncDarkMode() {
        themeFlowJob?.cancel()
        themeFlowJob =
            coroutineScope.launch {
                getDarkModeFlowUseCase().collectLatest { update { copy(isDark = it) } }
            }
    }

    private fun saveWindowSize(windowSize: WindowSize) {
        coroutineScope.launch {
            saveWindowSizeUseCase(windowSize)
        }
    }

    private fun restoreWindowSize() {
        coroutineScope.launch {
            val size = getWindowSizeUseCase()
            update { copy(size = size) }
        }
    }

    private fun checkAdbServer() {
        coroutineScope.launch {
            val dialog = if (startAdbUseCase()) MainDialog.Empty else MainDialog.AdbError
            update { copy(dialog = dialog) }
        }
    }

    private fun syncLanguage() {
        coroutineScope.launch {
            val type = getLanguageUseCase()
            Language.switch(type)
            update { copy(language = type) }
        }
    }

    private fun syncAccentColor() {
        coroutineScope.launch {
            val accentColor = getAccentColorUseCase()
            update { copy(accentColor = accentColor) }
        }
    }
}
