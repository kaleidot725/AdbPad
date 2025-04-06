package jp.kaleidot725.adbpad

import jp.kaleidot725.adbpad.core.mvi.MVIAction
import jp.kaleidot725.adbpad.core.mvi.MVIBase
import jp.kaleidot725.adbpad.core.mvi.MVISideEffect
import jp.kaleidot725.adbpad.core.mvi.MVIState
import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.domain.model.setting.WindowSize
import jp.kaleidot725.adbpad.domain.usecase.adb.StartAdbUseCase
import jp.kaleidot725.adbpad.domain.usecase.language.GetLanguageUseCase
import jp.kaleidot725.adbpad.domain.usecase.refresh.RefreshUseCase
import jp.kaleidot725.adbpad.domain.usecase.theme.GetDarkModeFlowUseCase
import jp.kaleidot725.adbpad.domain.usecase.window.GetWindowSizeUseCase
import jp.kaleidot725.adbpad.domain.usecase.window.SaveWindowSizeUseCase
import jp.kaleidot725.adbpad.ui.screen.command.CommandStateHolder
import jp.kaleidot725.adbpad.ui.screen.device.DeviceStateHolder
import jp.kaleidot725.adbpad.ui.screen.screenshot.ScreenshotStateHolder
import jp.kaleidot725.adbpad.ui.screen.setting.SettingStateHolder
import jp.kaleidot725.adbpad.ui.screen.text.TextCommandStateHolder
import jp.kaleidot725.adbpad.ui.section.top.TopStateHolder
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainStateHolder(
    val commandStateHolder: CommandStateHolder,
    val textCommandStateHolder: TextCommandStateHolder,
    val screenshotStateHolder: ScreenshotStateHolder,
    val topStateHolder: TopStateHolder,
    val deviceStateHolder: DeviceStateHolder,
    val settingStateHolder: SettingStateHolder,
    private val getWindowSizeUseCase: GetWindowSizeUseCase,
    private val saveWindowSizeUseCase: SaveWindowSizeUseCase,
    private val startAdbUseCase: StartAdbUseCase,
    private val getDarkModeFlowUseCase: GetDarkModeFlowUseCase,
    private val getLanguageUseCase: GetLanguageUseCase,
    private val refreshUseCase: RefreshUseCase,
) : MVIBase<MainState, MainAction, MainSideEffect>(initialUiState = MainState()) {
    private val children: List<MVIBase<out MVIState, out MVIAction, out MVISideEffect>> =
        listOf(
            commandStateHolder,
            textCommandStateHolder,
            screenshotStateHolder,
            topStateHolder,
        )

    override fun onSetup() {
        restoreWindowSize()
        startSyncDarkMode()
        checkAdbServer()
        syncLanguage()
        children.forEach { it.onSetup() }
    }

    override fun onRefresh() {
        startSyncDarkMode()
        checkAdbServer()
        syncLanguage()
        refreshUseCase()
        children.forEach { it.onRefresh() }
    }

    override fun onAction(uiAction: MainAction) {
        when (uiAction) {
            is MainAction.OpenSetting -> openSetting()
            is MainAction.OpenDevice -> openDevice()
            is MainAction.SaveSetting -> saveSetting(uiAction.windowSize)
            is MainAction.ClickCategory -> clickCategory(uiAction.category)
        }
    }

    private fun saveSetting(windowSize: WindowSize) {
        saveWindowSize(windowSize)
    }

    private fun openSetting() {
        update { copy(dialog = MainDialog.Setting) }
    }

    private fun openDevice() {
        update { copy(dialog = MainDialog.Device) }
    }

    private fun clickCategory(category: MainCategory) {
        update { copy(category = category) }
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
            val dialog = if (startAdbUseCase()) null else MainDialog.AdbError
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
}
