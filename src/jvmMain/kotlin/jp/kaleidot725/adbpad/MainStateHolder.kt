package jp.kaleidot725.adbpad

import jp.kaleidot725.adbpad.model.data.Event
import jp.kaleidot725.adbpad.view.common.ParentStateHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainStateHolder : ParentStateHolder {
    private val coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main + Dispatchers.IO)
    val state: MainState = MainState()

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()
    val event: SharedFlow<Event> = _event

    override fun setup() {
        state.children.forEach { it.setup() }
        state.childrenEvent.forEach { flow ->
            coroutineScope.launch {
                flow.collectLatest {
                    _event.emit(it)
                }
            }
        }
    }

    override fun dispose() {
        state.children.forEach { it.dispose() }
    }
}
