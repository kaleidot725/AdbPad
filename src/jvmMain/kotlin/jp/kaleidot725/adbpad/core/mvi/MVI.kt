package jp.kaleidot725.adbpad.core.mvi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface MVI<UiState : MVIState, UiAction : MVIAction, SideEffect : MVISideEffect> {
    val coroutineScope: CoroutineScope
    val state: StateFlow<UiState>
    val currentState: UiState
    val sideEffect: Flow<SideEffect>

    fun onSetup()

    fun onAction(uiAction: UiAction)

    fun onRefresh()

    fun onDispose()

    fun update(block: UiState.() -> UiState)

    suspend fun sideEffect(effect: SideEffect)
}
