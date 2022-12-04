package jp.kaleidot725.adbpad.view.component.text

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.view.component.RunningIndicator

@Composable
fun InputTextActionMenu(
    inputText: String,
    onTextChange: (String) -> Unit,
    onSend: () -> Unit,
    isSending: Boolean,
    canSend: Boolean,
    onSave: () -> Unit,
    canSave: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            value = inputText,
            onValueChange = { onTextChange(it) },
            modifier = Modifier
                .weight(0.9f, true)
                .fillMaxHeight()
        )

        Button(
            enabled = canSave,
            onClick = { onSave() },
            modifier = Modifier.fillMaxHeight().width(85.dp)
        ) {
            Text(
                text = Language.SAVE,
                textAlign = TextAlign.Center
            )
        }

        Button(
            enabled = canSend,
            onClick = { onSend() },
            modifier = Modifier.fillMaxHeight().width(85.dp)
        ) {
            when (isSending) {
                true -> RunningIndicator()
                else -> {
                    Text(
                        text = Language.SEND,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun InputTextActionMenu_Preview() {
    InputTextActionMenu(
        inputText = "INPUT TEXT SAMPLE",
        onSend = {},
        isSending = false,
        canSend = true,
        onSave = {},
        canSave = true,
        onTextChange = {},
        modifier = Modifier.height(50.dp)
    )
}
