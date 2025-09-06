package jp.kaleidot725.adbpad.ui.screen.command.component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.ViewList
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.ui.screen.command.model.CommandLayoutMode

@Composable
fun CommandLayoutToggle(
    layoutMode: CommandLayoutMode,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier,
) {
    IconButton(
        onClick = onToggle,
        modifier = modifier,
    ) {
        Icon(
            imageVector =
                when (layoutMode) {
                    CommandLayoutMode.CARD -> Icons.Default.ViewList // Show list icon when in card mode
                    CommandLayoutMode.LIST -> Icons.Default.GridView // Show grid icon when in list mode
                },
            contentDescription =
                when (layoutMode) {
                    CommandLayoutMode.CARD -> "Switch to List View"
                    CommandLayoutMode.LIST -> "Switch to Card View"
                },
            modifier = Modifier.size(24.dp),
        )
    }
}

@Preview
@Composable
private fun CommandLayoutToggle_Card_Preview() {
    CommandLayoutToggle(
        layoutMode = CommandLayoutMode.CARD,
        onToggle = {},
    )
}

@Preview
@Composable
private fun CommandLayoutToggle_List_Preview() {
    CommandLayoutToggle(
        layoutMode = CommandLayoutMode.LIST,
        onToggle = {},
    )
}
