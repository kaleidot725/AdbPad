package jp.kaleidot725.adbpad.ui.screen.newdisplay.component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import jp.kaleidot725.adbpad.ui.component.card.CommonItemCard
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ProfileListItem(
    name: String,
    lastModified: Long,
    selected: Boolean,
    onClick: () -> Unit,
) {
    val dateFormat = remember { SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault()) }
    CommonItemCard(
        title = name,
        details = dateFormat.format(Date(lastModified)),
        isSelected = selected,
        onClick = onClick,
    )
}

@Preview
@Composable
private fun ProfileListItemPreview() {
    ProfileListItem(
        name = "Sample Profile",
        lastModified = System.currentTimeMillis(),
        selected = true,
        onClick = {},
    )
}
