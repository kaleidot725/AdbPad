// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import jp.kaleidot725.adbpad.MainStateHolder
import jp.kaleidot725.adbpad.view.common.command.CommandList
import jp.kaleidot725.adbpad.view.resource.StringRes

fun main() = application {
    Window(title = StringRes.WINDOW_TITLE, onCloseRequest = ::exitApplication) {
        val stateHolder by remember { mutableStateOf(MainStateHolder()) }
        DisposableEffect(stateHolder) {
            stateHolder.setup()
            onDispose { stateHolder.dispose() }
        }

        val state by stateHolder.state.collectAsState()
        CommandList(
            commands = state.commands,
            onExecute = { stateHolder.executeCommand(it) }
        )
    }
}
