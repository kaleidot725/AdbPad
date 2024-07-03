package jp.kaleidot725.adbpad.ui.screen.menu.command.component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import jp.kaleidot725.adbpad.domain.model.command.NormalCommandCategory

@Composable
fun CommandTab(
    filtered: NormalCommandCategory?,
    onClick: (NormalCommandCategory?) -> Unit,
) {
    Row {
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
    CommandTab(null) {}
}
