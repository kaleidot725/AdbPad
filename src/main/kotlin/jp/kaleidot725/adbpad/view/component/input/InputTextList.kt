package jp.kaleidot725.adbpad.view.component.input

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.model.data.InputText

@Composable
fun InputTextList(
    inputTexts: List<InputText>,
    onExecute: (InputText) -> Unit,
    onDelete: (InputText) -> Unit,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        if (inputTexts.isNotEmpty()) {
            Column(verticalArrangement = verticalArrangement) {
                inputTexts.forEach { text ->
                    InputTestItem(
                        text = text,
                        onExecute = { onExecute(text) },
                        onDelete = { onDelete(text) },
                        modifier = Modifier.height(60.dp).fillMaxWidth()
                    )
                }
            }
        } else {
            Text(
                text = "入力テキストがありません",
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}
