package jp.kaleidot725.adbpad.ui.screen.command.component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Diamond
import com.composables.icons.lucide.View
import com.composables.icons.lucide.Wifi
import jp.kaleidot725.adbpad.domain.model.command.NormalCommandCategory

@Composable
fun CommandTab(
    modifier: Modifier = Modifier,
    filtered: NormalCommandCategory,
    onClick: (NormalCommandCategory) -> Unit,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        CommandTabItem(
            title = NormalCommandCategory.ALL.toString(),
            icon = Lucide.Diamond,
            isSelected = filtered == NormalCommandCategory.ALL,
            onClick = { onClick(NormalCommandCategory.ALL) },
        )

        CommandTabItem(
            title = NormalCommandCategory.UI.toString(),
            icon = Lucide.View,
            isSelected = filtered == NormalCommandCategory.UI,
            onClick = { onClick(NormalCommandCategory.UI) },
        )

        CommandTabItem(
            title = NormalCommandCategory.COM.toString(),
            icon = Lucide.Wifi,
            isSelected = filtered == NormalCommandCategory.COM,
            onClick = { onClick(NormalCommandCategory.COM) },
        )
    }
}

@Preview
@Composable
private fun CommandTabPreview() {
    CommandTab(filtered = NormalCommandCategory.ALL) {}
}
