package jp.kaleidot725.adbpad.view.screen

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.domain.model.command.NormalCommand
import jp.kaleidot725.adbpad.view.component.command.CommandList

@Composable
fun CommandScreen(
    commands: List<NormalCommand>,
    canExecute: Boolean,
    onExecute: (NormalCommand) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        CommandList(
            commands = commands,
            canExecute = canExecute,
            onExecute = onExecute,
            modifier = Modifier.fillMaxSize(),
        )
    }
}

@Preview
@Composable
private fun CommandScreen_Preview() {
    CommandScreen(
        commands = listOf(NormalCommand.DarkThemeOn(), NormalCommand.DarkThemeOff(), NormalCommand.WifiOn()),
        canExecute = true,
        onExecute = {},
    )
}
