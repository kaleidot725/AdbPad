package jp.kaleidot725.adbpad.view.common.command

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.model.data.Command
import jp.kaleidot725.adbpad.view.resource.StringRes

@Composable
fun CommandList(
    commands: List<Command>,
    onExecute: (Command) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        if (commands.isNotEmpty()) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                commands.forEach { command ->
                    CommandItem(
                        title = command.title,
                        detail = command.details,
                        onExecute = { onExecute(command) },
                        modifier = Modifier.fillMaxWidth().padding(2.dp)
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
            commands = listOf(Command.DarkThemeOn, Command.DarkThemeOff),
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
