package jp.kaleidot725.adbpad.view.component.navigationrail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun NavigationRailItem(
    icon: ImageVector,
    contentDescription: String?,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    val borderStroke =
        if (isSelected) {
            BorderStroke(2.dp, MaterialTheme.colors.primary)
        } else {
            BorderStroke(0.dp, Color.Transparent)
        }

    Box(
        modifier =
            Modifier
                .size(36.dp)
                .clip(RoundedCornerShape(8.dp))
                .border(borderStroke, RoundedCornerShape(8.dp))
                .clickable(onClick = onClick),
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            modifier = Modifier.size(24.dp).align(Alignment.Center),
        )
    }
}
