package jp.kaleidot725.adbpad.core.mvi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun <State : MVIState, Action : MVIAction, SideEffect : MVISideEffect> MVILifecycleContent(
    mvi: MVIBase<State, Action, SideEffect>,
    onCreated: () -> Unit = {},
    onSideEffect: (SideEffect) -> Unit = {},
    onDeleted: () -> Unit = {},
    content: @Composable ((State, ((Action) -> Unit)) -> Unit) = { _, _ -> },
) {
    val state by mvi.state.collectAsState()
    val onAction = mvi::onAction
    LaunchedEffect(Unit) {
        onCreated()
        mvi.onSetup()
    }
    LaunchedEffect(Unit) {
        mvi.sideEffect.collect { onSideEffect(it) }
    }
    DisposableEffect(Unit) {
        onDeleted()
        onDispose { mvi.onReset() }
    }
    content(state, onAction)
}

@Composable
fun <State : MVIState, Action : MVIAction, SideEffect : MVISideEffect> MVIContent(
    mvi: MVIBase<State, Action, SideEffect>,
    onSideEffect: (SideEffect) -> Unit = {},
    content: @Composable (State, (Action) -> Unit) -> Unit = { _, _ -> },
) {
    val state by mvi.state.collectAsState()
    val onAction = mvi::onAction
    LaunchedEffect(mvi) { mvi.sideEffect.collect { onSideEffect(it) } }
    content(state, onAction)
}
