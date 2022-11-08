package jp.kaleidot725.adbpad

import jp.kaleidot725.adbpad.domain.model.Event
import jp.kaleidot725.adbpad.domain.model.WindowSize
import jp.kaleidot725.adbpad.domain.usecase.event.GetEventFlowUseCase
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
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainStateHolder(
    val menuStateHolder: MenuStateHolder,
    val commandStateHolder: CommandStateHolder,
    val textCommandStateHolder: TextCommandStateHolder,
    val screenshotStateHolder: ScreenshotStateHolder,
    val getEventFlowUseCase: GetEventFlowUseCase,
    val getWindowSizeUseCase: GetWindowSizeUseCase,
    val saveWindowSizeUseCase: SaveWindowSizeUseCase
) : ParentStateHolder {
    private val coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main + Dispatchers.IO)
    private val windowSize: MutableStateFlow<WindowSize> = MutableStateFlow(WindowSize.UNKNOWN)
    val event: SharedFlow<Event> = getEventFlowUseCase()
    val state: StateFlow<MainState> = windowSize.map {
        MainState(it)
    }.stateIn(coroutineScope, SharingStarted.WhileSubscribed(), MainState())

    private val children: List<ChildStateHolder<*>> = listOf(
        menuStateHolder, commandStateHolder, textCommandStateHolder, screenshotStateHolder
    )

    override fun setup() {
        children.forEach { it.setup() }
        coroutineScope.launch { windowSize.value = getWindowSizeUseCase() }
    }

    fun saveSetting(windowSize: WindowSize) {
        coroutineScope.launch { saveWindowSizeUseCase(windowSize) }
    }

    override fun dispose() {
        children.forEach { it.dispose() }
    }
}
