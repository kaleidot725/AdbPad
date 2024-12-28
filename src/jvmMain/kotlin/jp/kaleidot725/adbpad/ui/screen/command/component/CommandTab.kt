package jp.kaleidot725.adbpad.ui.screen.command.component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import jp.kaleidot725.adbpad.domain.model.command.NormalCommandCategory

@Composable
fun CommandTab(
    modifier: Modifier = Modifier,
    filtered: NormalCommandCategory?,
    onClick: (NormalCommandCategory?) -> Unit,
) {
    Row(modifier) {
        CommandTabItem(
            title = "All",
            isSelected = filtered == null,
            onClick = { onClick(null) },
        )

        CommandTabItem(
            title = NormalCommandCategory.UI.toString(),
            isSelected = filtered == NormalCommandCategory.UI,
            onClick = { onClick(NormalCommandCategory.UI) },
        )

        CommandTabItem(
            title = NormalCommandCategory.Communication.toString(),
            isSelected = filtered == NormalCommandCategory.Communication,
            onClick = { onClick(NormalCommandCategory.Communication) },
        )
    }
}

@Preview
@Composable
private fun CommandTabPreview() {
    CommandTab(filtered = null) {}
}
