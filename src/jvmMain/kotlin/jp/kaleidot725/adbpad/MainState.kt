package jp.kaleidot725.adbpad

import jp.kaleidot725.adbpad.domain.model.Event
import jp.kaleidot725.adbpad.view.common.ChildStateHolder
import jp.kaleidot725.adbpad.view.screen.command.CommandStateHolder
import jp.kaleidot725.adbpad.view.screen.input.InputTextStateHolder
import jp.kaleidot725.adbpad.view.screen.menu.MenuStateHolder
import jp.kaleidot725.adbpad.view.screen.screenshot.ScreenshotStateHolder
import kotlinx.coroutines.flow.SharedFlow

data class MainState(
    val menuStateHolder: MenuStateHolder = MenuStateHolder(),
    val commandStateHolder: CommandStateHolder = CommandStateHolder(),
    val inputTextStateHolder: InputTextStateHolder = InputTextStateHolder(),
    val screenshotStateHolder: ScreenshotStateHolder = ScreenshotStateHolder()
) {
    val children: List<ChildStateHolder<*>> = listOf(
        menuStateHolder, commandStateHolder, inputTextStateHolder, screenshotStateHolder
    )
    val childrenEvent: List<SharedFlow<Event>> = children.map { it.event }
}
