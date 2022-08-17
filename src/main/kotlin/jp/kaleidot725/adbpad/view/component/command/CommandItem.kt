package jp.kaleidot725.adbpad.view.component.command

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CommandItem(
    title: String,
    detail: String,
    onExecute: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(modifier) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = title, maxLines = 1, modifier = Modifier.fillMaxWidth())
            Text(text = detail, maxLines = 3, modifier = Modifier.fillMaxWidth().weight(weight = 0.5f, fill = true))
            Button(onClick = { onExecute() }, modifier = Modifier.align(Alignment.End)) {
                Text("実行")
            }
        }
    }
}

@Preview
@Composable
private fun CommandItem_Preview() {
    CommandItem(
        title = "ダークテーマON",
        detail = "端末のダークテーマ設定をONにします",
        onExecute = {},
        modifier = Modifier.size(200.dp)
    )
}