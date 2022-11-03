package jp.kaleidot725.adbpad.view.component.input

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.domain.model.Language

@Composable
fun InputTextItem(
    text: String,
    onSend: () -> Unit,
    canSend: Boolean,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(modifier, elevation = 1.dp) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = text,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(weight = 0.8f, fill = true)
                    .align(Alignment.CenterVertically)
            )
            Button(
                onClick = { onDelete() },
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text(Language.DELETE)
            }
            Button(
                onClick = { onSend() },
                enabled = canSend,
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text(Language.SEND)
            }
        }
    }
}

@Preview
@Composable
private fun CommandItem_Preview() {
    InputTextItem(
        text = "いろはにほへと",
        onSend = {},
        canSend = true,
        onDelete = {},
        modifier = Modifier.fillMaxWidth().height(50.dp)
    )
}
