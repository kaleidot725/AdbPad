package jp.kaleidot725.adbpad.ui.screen.text.component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.domain.model.command.TextCommand
import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.ui.common.dummy.TextCommandDummy
import jp.kaleidot725.adbpad.ui.common.resource.defaultBorder
import jp.kaleidot725.adbpad.ui.component.DefaultTextField

@Composable
fun TextCommandEditor(
    command: TextCommand,
    onUpdateTitle: (id: String, value: String) -> Unit,
    onUpdateText: (id: String, value: String) -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            DefaultTextField(
                id = command.id,
                initialText = command.title,
                placeHolder = Language.textCommandUnTitle,
                onUpdateText = { onUpdateTitle(command.id, it) },
                modifier = Modifier.weight(1.0f).height(48.dp).padding(horizontal = 12.dp),
            )

            IconButton(
                onClick = onDelete,
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "",
                )
            }
        }

        Divider(modifier = Modifier.height(1.dp).fillMaxWidth().defaultBorder())

        DefaultTextField(
            id = command.id,
            initialText = command.text,
            placeHolder = "",
            maxLines = Int.MAX_VALUE,
            onUpdateText = { onUpdateText(command.id, it) },
            modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp, horizontal = 12.dp),
        )
    }
}

@Preview
@Composable
private fun Preview() {
    TextCommandEditor(
        command = TextCommandDummy.value,
        onUpdateTitle = { _, _ -> },
        onUpdateText = { _, _ -> },
        onDelete = {},
    )
}
