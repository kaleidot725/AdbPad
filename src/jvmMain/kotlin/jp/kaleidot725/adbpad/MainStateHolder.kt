package jp.kaleidot725.adbpad

import jp.kaleidot725.adbpad.domain.model.Dialog
import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.domain.model.log.Event
import jp.kaleidot725.adbpad.domain.model.setting.WindowSize
import jp.kaleidot725.adbpad.domain.usecase.adb.StartAdbUseCase
import jp.kaleidot725.adbpad.domain.usecase.event.GetEventFlowUseCase
import jp.kaleidot725.adbpad.domain.usecase.language.GetLanguageUseCase
import jp.kaleidot725.adbpad.domain.usecase.theme.GetDarkModeFlowUseCase
import jp.kaleidot725.adbpad.domain.usecase.window.GetWindowSizeUseCase
import jp.kaleidot725.adbpad.domain.usecase.window.SaveWindowSizeUseCase
import jp.kaleidot725.adbpad.view.common.ChildStateHolder
import jp.kaleidot725.adbpad.view.common.ParentStateHolder
import jp.kaleidot725.adbpad.view.screen.command.CommandStateHolder
import jp.kaleidot725.adbpad.view.screen.menu.MenuStateHolder
import jp.kaleidot725.adbpad.view.screen.screenshot.ScreenshotStateHolder
import jp.kaleidot725.adbpad.view.screen.text.TextCommandStateHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainStateHolder(
    val menuStateHolder: MenuStateHolder,
    val commandStateHolder: CommandStateHolder,
    val textCommandStateHolder: TextCommandStateHolder,
    val screenshotStateHolder: ScreenshotStateHolder,
    val getEventFlowUseCase: GetEventFlowUseCase,
    val getWindowSizeUseCase: GetWindowSizeUseCase,
    val saveWindowSizeUseCase: SaveWindowSizeUseCase,
    val startAdbUseCase: StartAdbUseCase,
    val getDarkModeFlowUseCase: GetDarkModeFlowUseCase,
    val getLanguageUseCase: GetLanguageUseCase,
) : ParentStateHolder {
    private val coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main + Dispatchers.IO)
    private val language: MutableStateFlow<Language.Type> = MutableStateFlow(Language.Type.ENGLISH)
    private val windowSize: MutableStateFlow<WindowSize> = MutableStateFlow(WindowSize.UNKNOWN)
    private val isDark: MutableStateFlow<Boolean> = MutableStateFlow(true)
    private val dialog: MutableStateFlow<Dialog?> = MutableStateFlow(null)
    val event: SharedFlow<Event> = getEventFlowUseCase()
    val state: StateFlow<MainState> =
        combine(language, isDark, windowSize, dialog) { language, isDark, windowSize, dialog ->
            MainState(language, isDark, windowSize, dialog)
        }.stateIn(coroutineScope, SharingStarted.WhileSubscribed(), MainState())

    private val children: List<ChildStateHolder<*>> = listOf(
        menuStateHolder, commandStateHolder, textCommandStateHolder, screenshotStateHolder
    )

    init {
        startSyncDarkMode()
        restoreWindowSize()
        checkAdbServer()
        syncLanguage()
    }

    override fun setup() {
        children.forEach { it.setup() }
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

    fun closeSetting() {
        startSyncDarkMode()
        checkAdbServer()
        syncLanguage()
    }

    private var themeFlowJob: Job? = null
    private fun startSyncDarkMode() {
        themeFlowJob?.cancel()
        themeFlowJob = coroutineScope.launch {
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
}
