package jp.kaleidot725.adbpad.view.component.text

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardTab
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.KeyboardTab
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jp.kaleidot725.adbpad.view.common.resource.defaultBorder
import jp.kaleidot725.adbpad.view.component.RunningIndicator

@Composable
fun InputTextActionMenu(
    inputText: String,
    onTextChange: (String) -> Unit,
    onSend: () -> Unit,
    isSending: Boolean,
    canSendTab: Boolean,
    onSendTab: () -> Unit,
    isSendingTag: Boolean,
    canSend: Boolean,
    onSave: () -> Unit,
    canSave: Boolean,
    modifier: Modifier = Modifier,
) {
    var text by remember { mutableStateOf(inputText) }
    Row(
        modifier =
            modifier
                .clip(RoundedCornerShape(4.dp))
                .defaultBorder()
                .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                onTextChange(it)
            },
            cursorBrush = SolidColor(MaterialTheme.colors.onBackground),
            textStyle =
                TextStyle(
                    fontSize = 20.sp,
                    color = MaterialTheme.colors.onBackground,
                ),
            modifier =
                Modifier
                    .weight(0.9f, true)
                    .align(Alignment.CenterVertically),
        )

        IconButton(
            enabled = canSave,
            onClick = { onSave() },
            modifier = Modifier.fillMaxHeight(),
        ) {
            Icon(
                imageVector = Icons.Default.Save,
                contentDescription = "Save",
            )
        }

        IconButton(
            enabled = canSendTab,
            onClick = { onSendTab() },
            modifier = Modifier.fillMaxHeight(),
        ) {
            when (isSendingTag) {
                true -> RunningIndicator(color = MaterialTheme.colors.primary)
                else -> {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.KeyboardTab,
                        contentDescription = "Save",
                    )
                }
            }
        }

        IconButton(
            enabled = canSend,
            onClick = { onSend() },
            modifier = Modifier.fillMaxHeight(),
        ) {
            when (isSending) {
                true -> RunningIndicator(color = MaterialTheme.colors.primary)
                else -> {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.Send,
                        contentDescription = "Save",
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
        canSendTab = false,
        onSendTab = {},
        onSave = {},
        canSave = true,
        onTextChange = {},
        isSendingTag = false,
        modifier = Modifier.height(50.dp),
    )
}
