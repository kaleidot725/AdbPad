package jp.kaleidot725.adbpad.ui.component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Power
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jp.kaleidot725.adbpad.ui.common.resource.selectedBackground

@Composable
fun NavigationRailItem(
    label: String = "",
    icon: ImageVector,
    contentDescription: String?,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .size(50.dp)
            .clip(RoundedCornerShape(8.dp))
            .selectedBackground(isSelected)
            .clickable(onClick = onClick)
            .padding(4.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp),
            modifier = Modifier.align(Alignment.Center)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                modifier = Modifier.size(20.dp).align(Alignment.CenterHorizontally),
            )

            if (label.isNotEmpty()) {
                AutoSizableText(
                    text = label,
                    style = TextStyle(textAlign = TextAlign.Center),
                    maxFontSize = 12.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}

@Preview
@Composable
private fun NavigationRailItemPreview() {
    NavigationRailItem(
        label = "Power",
        icon = Icons.Default.Power,
        contentDescription = null,
        isSelected = false,
        onClick = {}
    )
}
