package jp.kaleidot725.adbpad.ui.component.rail

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Power
import jp.kaleidot725.adbpad.ui.common.resource.clickableBackground

@Composable
fun NavigationRailItem(
    label: String,
    icon: ImageVector,
    contentDescription: String?,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickableBackground(isSelected = isSelected, shape = RoundedCornerShape(8.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 8.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().align(Alignment.Center),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                modifier = Modifier.size(16.dp),
            )

            val textStyle =
                if (isSelected) {
                    MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium)
                } else {
                    MaterialTheme.typography.bodySmall
                }

            Text(
                text = label,
                style = textStyle,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
    }
}

@Preview
@Composable
private fun NavigationRailItemPreview() {
    NavigationRailItem(
        label = "Power",
        icon = Lucide.Power,
        contentDescription = null,
        isSelected = true,
        onClick = {},
    )
}
