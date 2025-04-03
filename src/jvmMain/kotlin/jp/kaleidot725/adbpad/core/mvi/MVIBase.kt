package jp.kaleidot725.adbpad.core.mvi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onSubscription
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class MVIBase<UiState : MVIState, UiAction : MVIAction, SideEffect : MVISideEffect>(
    initialUiState: UiState,
) {
    val coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main + Dispatchers.IO)

    private val uiState = MutableStateFlow(initialUiState)
    val state: StateFlow<UiState> by lazy {
        uiState
            .onSubscription {
                onSetup()
            }.stateIn(
                coroutineScope,
                SharingStarted.WhileSubscribed(),
                initialUiState,
            )
    }
    val currentState: UiState get() = state.value
    private val _sideEffect by lazy { Channel<SideEffect>() }
    val sideEffect: Flow<SideEffect> by lazy { _sideEffect.receiveAsFlow() }

    abstract fun onSetup()

    abstract fun onAction(uiAction: UiAction)

    abstract fun onRefresh()

    open fun onDispose() {
        coroutineScope.cancel()
    }

    fun update(block: UiState.() -> UiState) {
        uiState.update { block(it) }
    }

    fun sideEffect(effect: SideEffect) {
        coroutineScope.launch { _sideEffect.send(effect) }
    }
}
