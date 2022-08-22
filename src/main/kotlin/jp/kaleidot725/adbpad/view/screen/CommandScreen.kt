package jp.kaleidot725.adbpad.view.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.model.data.Command
import jp.kaleidot725.adbpad.view.component.command.CommandList

@Composable
fun CommandScreen(
    commands: List<Command>,
    onExecute: (Command) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        CommandList(
            commands = commands,
            onExecute = onExecute,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        )
    }
}
