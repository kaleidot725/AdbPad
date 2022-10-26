package jp.kaleidot725.adbpad

import jp.kaleidot725.adbpad.view.common.StateHolder
import jp.kaleidot725.adbpad.view.screen.command.CommandStateHolder
import jp.kaleidot725.adbpad.view.screen.input.InputTextStateHolder
import jp.kaleidot725.adbpad.view.screen.menu.MenuStateHolder
import jp.kaleidot725.adbpad.view.screen.screenshot.ScreenshotStateHolder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainStateHolder : StateHolder<Unit> {
    val menuStateHolder = MenuStateHolder()
    val commandStateHolder = CommandStateHolder()
    val inputTextStateHolder = InputTextStateHolder()
    val screenshotStateHolder = ScreenshotStateHolder()

    override val state: StateFlow<Unit> = MutableStateFlow(Unit)
    override fun setup() {
        menuStateHolder.setup()
        commandStateHolder.setup()
        inputTextStateHolder.setup()
        screenshotStateHolder.setup()
    }

    override fun dispose() {
        menuStateHolder.dispose()
        commandStateHolder.dispose()
        inputTextStateHolder.dispose()
        screenshotStateHolder.dispose()
    }
}
