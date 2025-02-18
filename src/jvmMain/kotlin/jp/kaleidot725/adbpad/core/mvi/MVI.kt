package jp.kaleidot725.adbpad.core.mvi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface MVI<UiState : MVIState, UiAction : MVIAction, SideEffect : MVISideEffect> {
    val uiState: StateFlow<UiState>
    val sideEffect: Flow<SideEffect>

    fun onAction(uiAction: UiAction)

    fun update(block: UiState.() -> UiState)

    fun CoroutineScope.sideEffect(effect: SideEffect)
}