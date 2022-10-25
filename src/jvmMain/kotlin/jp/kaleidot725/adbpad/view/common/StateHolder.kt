package jp.kaleidot725.adbpad.view.common

import kotlinx.coroutines.flow.StateFlow

interface StateHolder<T> {
    val state: StateFlow<T>
    fun setup()
    fun dispose()
}