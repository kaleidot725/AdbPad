package jp.kaleidot725.adbpad.view.common

import kotlinx.coroutines.flow.StateFlow

interface ChildStateHolder<T> {
    val state: StateFlow<T>

    fun setup()

    fun refresh()

    fun dispose()
}
