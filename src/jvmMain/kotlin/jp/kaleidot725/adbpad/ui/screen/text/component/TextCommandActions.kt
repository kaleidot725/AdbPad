package jp.kaleidot725.adbpad.ui.screen.text.component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.domain.model.command.TextCommand
import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.ui.common.dummy.TextCommandDummy
import jp.kaleidot725.adbpad.ui.component.RunningIndicator

@Composable
fun TextCommandActions(
    command: TextCommand,
    canSend: Boolean,
    onSendText: () -> Unit,
    onUpdateEnableTab: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
    ) {
        Spacer(
            modifier = Modifier.weight(1.0f),
        )

        Button(
            onClick = onSendText,
            enabled = canSend,
            modifier = Modifier.padding(horizontal = 12.dp),
        ) {
            when {
                command.isRunning -> RunningIndicator()
                else -> Text(text = Language.send)
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    TextCommandActions(
        command = TextCommandDummy.value,
        canSend = true,
        onSendText = {},
        onUpdateEnableTab = {},
    )
}
