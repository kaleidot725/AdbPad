package jp.kaleidot725.adbpad.view.common

import jp.kaleidot725.adbpad.domain.model.Event
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface ChildStateHolder<T> {
    val state: StateFlow<T>
    val event: SharedFlow<Event>
    fun setup()
    fun dispose()
}
