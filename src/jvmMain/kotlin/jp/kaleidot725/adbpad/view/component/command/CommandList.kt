package jp.kaleidot725.adbpad.view.component.command

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.domain.model.Command
import jp.kaleidot725.adbpad.view.common.resource.StringRes

@Composable
fun CommandList(
    commands: List<Command>,
    onExecute: (Command) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        if (commands.isNotEmpty()) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(250.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(commands) { command ->
                    CommandItem(
                        title = command.title,
                        detail = command.details,
                        isRunning = command.isRunning,
                        onExecute = { onExecute(command) },
                        modifier = Modifier.height(200.dp).fillMaxWidth().padding(2.dp)
                    )
                }
            }
        } else {
            Text(
                text = StringRes.NOT_FOUND_COMMAND,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Preview
@Composable
private fun CommandList_Preview() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        CommandList(
            commands = listOf(Command.DarkThemeOn(), Command.DarkThemeOff(), Command.WifiOn()),
            onExecute = {},
            modifier = Modifier.fillMaxWidth().weight(0.5f)
        )

        CommandList(
            commands = emptyList(),
            onExecute = {},
            modifier = Modifier.fillMaxWidth().weight(0.5f).background(Color.LightGray)
        )
    }
}
