package jp.kaleidot725.adbpad

import jp.kaleidot725.adbpad.domain.model.Event
import jp.kaleidot725.adbpad.view.common.ChildStateHolder
import jp.kaleidot725.adbpad.view.common.ParentStateHolder
import jp.kaleidot725.adbpad.view.screen.command.CommandStateHolder
import jp.kaleidot725.adbpad.view.screen.input.InputTextStateHolder
import jp.kaleidot725.adbpad.view.screen.menu.MenuStateHolder
import jp.kaleidot725.adbpad.view.screen.screenshot.ScreenshotStateHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainStateHolder(
    val menuStateHolder: MenuStateHolder,
    val commandStateHolder: CommandStateHolder,
    val inputTextStateHolder: InputTextStateHolder,
    val screenshotStateHolder: ScreenshotStateHolder
) : ParentStateHolder {
    private val coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main + Dispatchers.IO)

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()
    val event: SharedFlow<Event> = _event

    private val children: List<ChildStateHolder<*>> = listOf(
        menuStateHolder, commandStateHolder, inputTextStateHolder, screenshotStateHolder
    )
    private val childrenEvent: List<SharedFlow<Event>> = children.map { it.event }

    override fun setup() {
        children.forEach { it.setup() }
        childrenEvent.forEach { flow ->
            coroutineScope.launch {
                flow.collectLatest {
                    _event.emit(it)
                }
            }
        }
    }

    override fun dispose() {
        children.forEach { it.dispose() }
    }
}
