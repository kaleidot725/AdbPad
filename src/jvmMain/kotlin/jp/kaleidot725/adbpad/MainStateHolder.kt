package jp.kaleidot725.adbpad

import jp.kaleidot725.adbpad.domain.model.Dialog
import jp.kaleidot725.adbpad.domain.model.Event
import jp.kaleidot725.adbpad.domain.model.setting.WindowSize
import jp.kaleidot725.adbpad.domain.usecase.adb.StartAdbUseCase
import jp.kaleidot725.adbpad.domain.usecase.event.GetEventFlowUseCase
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
    val getDarkModeFlowUseCase: GetDarkModeFlowUseCase
) : ParentStateHolder {
    private val coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main + Dispatchers.IO)
    private val windowSize: MutableStateFlow<WindowSize> = MutableStateFlow(WindowSize.UNKNOWN)
    private val isDark: MutableStateFlow<Boolean> = MutableStateFlow(true)
    private val dialog: MutableStateFlow<Dialog?> = MutableStateFlow(null)
    val event: SharedFlow<Event> = getEventFlowUseCase()
    val state: StateFlow<MainState> = combine(isDark, windowSize, dialog) { isDark, windowSize, dialog ->
        MainState(isDark, windowSize, dialog)
    }.stateIn(coroutineScope, SharingStarted.WhileSubscribed(), MainState())

    private val children: List<ChildStateHolder<*>> = listOf(
        menuStateHolder, commandStateHolder, textCommandStateHolder, screenshotStateHolder
    )
    
    init {
        startSyncDarkMode()
        coroutineScope.launch {
            windowSize.value = getWindowSizeUseCase()
            dialog.value = if (startAdbUseCase()) null else Dialog.AdbError
        }
    }

    override fun setup() {
        children.forEach { it.setup() }
    }

    fun saveSetting(windowSize: WindowSize) {
        coroutineScope.launch { saveWindowSizeUseCase(windowSize) }
    }

    fun openSetting() {
        dialog.value = Dialog.Setting
    }

    fun closeSetting() {
        startSyncDarkMode()
        coroutineScope.launch {
            dialog.value = if (startAdbUseCase()) null else Dialog.AdbError
        }
    }

    override fun dispose() {
        children.forEach { it.dispose() }
    }

    private var themeFlowJob: Job? = null
    private fun startSyncDarkMode() {
        themeFlowJob?.cancel()
        themeFlowJob = coroutineScope.launch {
            val flow = getDarkModeFlowUseCase()
            flow.collectLatest { isDark.value = it }
        }
    }
}
