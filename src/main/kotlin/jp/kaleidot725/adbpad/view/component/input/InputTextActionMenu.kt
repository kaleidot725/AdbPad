package jp.kaleidot725.adbpad.view.component.input

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.model.data.InputText
import jp.kaleidot725.adbpad.view.resource.String

@Composable
fun InputTextActionMenu(
    inputText: InputText,
    onTextChange: (InputText) -> Unit,
    onExecute: (InputText) -> Unit,
    onSave: (InputText) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            value = inputText.content,
            onValueChange = { onTextChange(InputText(content = it)) },
            modifier = Modifier
                .weight(0.9f, true)
                .fillMaxHeight()
        )

        Button(
            onClick = { onSave(inputText) },
            modifier = Modifier.fillMaxHeight().wrapContentWidth()
        ) {
            Text(
                text = String.SAVE,
                textAlign = TextAlign.Center
            )
        }

        Button(
            onClick = { onExecute(inputText) },
            modifier = Modifier.fillMaxHeight().wrapContentWidth()
        ) {
            Text(
                text = String.SEND,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
private fun InputTextActionMenu_Preview() {
    InputTextActionMenu(
        inputText = InputText("INPUT TEXT SAMPLE"),
        onExecute = {},
        onSave = {},
        onTextChange = {},
        modifier = Modifier.height(50.dp)
    )
}
