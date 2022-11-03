package jp.kaleidot725.adbpad.view.component.input

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
import jp.kaleidot725.adbpad.domain.model.Language

@Composable
fun InputTextActionMenu(
    inputText: String,
    onTextChange: (String) -> Unit,
    onSend: (String) -> Unit,
    canSend: Boolean,
    onSave: (String) -> Unit,
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
            onClick = { onSave(inputText) },
            modifier = Modifier.fillMaxHeight().width(85.dp)
        ) {
            Text(
                text = Language.SAVE,
                textAlign = TextAlign.Center
            )
        }

        Button(
            enabled = canSend,
            onClick = { onSend(inputText) },
            modifier = Modifier.fillMaxHeight().width(85.dp)
        ) {
            Text(
                text = Language.SEND,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
private fun InputTextActionMenu_Preview() {
    InputTextActionMenu(
        inputText = "INPUT TEXT SAMPLE",
        onSend = {},
        canSend = true,
        onSave = {},
        canSave = true,
        onTextChange = {},
        modifier = Modifier.height(50.dp)
    )
}
