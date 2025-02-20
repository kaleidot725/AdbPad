package jp.kaleidot725.adbpad.core.mvi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MVIDelegate<UiState : MVIState, UiAction : MVIAction, SideEffect : MVISideEffect> internal constructor(
    initialUiState: UiState,
) : MVI<UiState, UiAction, SideEffect> {
    override val coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main + Dispatchers.IO)

    private val _uiState = MutableStateFlow(initialUiState)
    override val state: StateFlow<UiState> = _uiState.asStateFlow()
    override val currentState: UiState get() = state.value
    private val _sideEffect by lazy { Channel<SideEffect>() }
    override val sideEffect: Flow<SideEffect> by lazy { _sideEffect.receiveAsFlow() }

    override fun onSetup() {}
    override fun onAction(uiAction: UiAction) {}
    override fun onRefresh() {}
    override fun onDispose() {}

    override fun update(block: UiState.() -> UiState) {
        _uiState.update { block(it) }
    }

    override suspend fun sideEffect(effect: SideEffect) {
        coroutineScope.launch { _sideEffect.send(effect) }
    }
}

fun <UiState : MVIState, UiAction : MVIAction, SideEffect : MVISideEffect> mvi(
    initialUiState: UiState,
): MVI<UiState, UiAction, SideEffect> = MVIDelegate(initialUiState)