package jp.kaleidot725.adbpad.view.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.model.Command
import jp.kaleidot725.adbpad.view.component.command.CommandList

@Composable
fun CommandPane(
    commands: List<Command>,
    onExecuteCommand: (Command) -> Unit
) {
    Surface(color = Color.LightGray) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize().padding(16.dp),
        ) {
            CommandList(
                commands = Command.values().toList(),
                onExecuteCommand = onExecuteCommand,
                minSize = 200.dp,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
