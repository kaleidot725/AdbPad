package jp.kaleidot725.adbpad

import jp.kaleidot725.adbpad.domain.model.Event
import jp.kaleidot725.adbpad.domain.usecase.event.GetEventFlowUseCase
import jp.kaleidot725.adbpad.view.common.ChildStateHolder
import jp.kaleidot725.adbpad.view.common.ParentStateHolder
import jp.kaleidot725.adbpad.view.screen.command.CommandStateHolder
import jp.kaleidot725.adbpad.view.screen.menu.MenuStateHolder
import jp.kaleidot725.adbpad.view.screen.screenshot.ScreenshotStateHolder
import jp.kaleidot725.adbpad.view.screen.text.TextCommandStateHolder
import kotlinx.coroutines.flow.SharedFlow

class MainStateHolder(
    val menuStateHolder: MenuStateHolder,
    val commandStateHolder: CommandStateHolder,
    val textCommandStateHolder: TextCommandStateHolder,
    val screenshotStateHolder: ScreenshotStateHolder,
    val getEventFlowUseCase: GetEventFlowUseCase,
) : ParentStateHolder {
    val event: SharedFlow<Event> = getEventFlowUseCase()

    private val children: List<ChildStateHolder<*>> = listOf(
        menuStateHolder, commandStateHolder, textCommandStateHolder, screenshotStateHolder
    )

    override fun setup() {
        children.forEach { it.setup() }
    }

    override fun dispose() {
        children.forEach { it.dispose() }
    }
}
