package jp.kaleidot725.adbpad.view.component.input

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.model.data.InputText

@Composable
fun InputTextFields(
    inputText: InputText,
    onTextChange: (InputText) -> Unit,
    onExecute: (InputText) -> Unit,
    onSave: (InputText) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
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
                text = "保存",
                textAlign = TextAlign.Center
            )
        }

        Button(
            onClick = { onExecute(inputText) },
            modifier = Modifier.fillMaxHeight().wrapContentWidth()
        ) {
            Text(
                text = "送信",
                textAlign = TextAlign.Center
            )
        }
    }
}
