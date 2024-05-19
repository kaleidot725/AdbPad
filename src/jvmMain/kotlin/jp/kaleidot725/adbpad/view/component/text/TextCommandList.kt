package jp.kaleidot725.adbpad.view.component.text

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.domain.model.command.TextCommand
import jp.kaleidot725.adbpad.domain.model.language.Language

@Composable
fun TextCommandList(
    commands: List<TextCommand>,
    onSend: (TextCommand) -> Unit,
    canSend: Boolean,
    onDelete: (TextCommand) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        if (commands.isNotEmpty()) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.verticalScroll(rememberScrollState()),
            ) {
                commands.forEach { command ->
                    TextCommandItem(
                        text = command.text,
                        isRunning = command.isRunning,
                        onSend = { onSend(command) },
                        canSend = canSend,
                        onDelete = { onDelete(command) },
                        modifier = Modifier.height(60.dp).fillMaxWidth().padding(2.dp),
                    )
                }
            }
        } else {
            Text(
                text = Language.notFoundInputText,
                modifier = Modifier.align(Alignment.Center),
            )
        }
    }
}

@Preview
@Composable
private fun TextCommandList_Preview() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        TextCommandList(
            commands = listOf(TextCommand("TEST1"), TextCommand("TEST2")),
            onSend = {},
            canSend = true,
            onDelete = {},
            modifier = Modifier.fillMaxWidth().weight(0.5f, true),
        )

        TextCommandList(
            commands = emptyList(),
            onSend = {},
            canSend = true,
            onDelete = {},
            modifier = Modifier.fillMaxWidth().weight(0.5f, true).background(Color.LightGray),
        )
    }
}
