package jp.kaleidot725.adbpad.view.component.input

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DoubleArrow
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
        TextField(
            value = inputText.content,
            onValueChange = { onTextChange(InputText(content = it)) },
            modifier = Modifier.weight(0.9f, true).fillMaxHeight()
        )

        Button(onClick = { onExecute(inputText) }) {
            Icon(
                imageVector = Icons.Default.DoubleArrow,
                contentDescription = "Run",
                modifier = Modifier.fillMaxHeight().wrapContentWidth()
            )
        }

        Button(onClick = { onSave(inputText) }) {
            Icon(
                imageVector = Icons.Default.Save,
                contentDescription = "Save",
                modifier = Modifier.fillMaxHeight().wrapContentWidth()
            )
        }
    }
}
