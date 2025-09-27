package jp.kaleidot725.adbpad.ui.component.rail

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.TooltipArea
import androidx.compose.foundation.TooltipPlacement
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Power
import jp.kaleidot725.adbpad.ui.common.resource.UserColor
import jp.kaleidot725.adbpad.ui.common.resource.clickableBackground

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NavigationRailItem(
    label: String = "",
    icon: ImageVector,
    contentDescription: String?,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    Box(
        modifier =
            Modifier
                .size(28.dp)
                .clip(RoundedCornerShape(4.dp))
                .clickableBackground(isSelected)
                .clickable(onClick = onClick),
    ) {
        TooltipArea(
            delayMillis = 300,
            tooltip = {
                Card(
                    modifier =
                        Modifier.border(1.dp, UserColor.getSplitterColor()),
                ) {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
                    )
                }
            },
            tooltipPlacement =
                TooltipPlacement.ComponentRect(
                    anchor = Alignment.CenterEnd,
                    alignment = Alignment.CenterEnd,
                    offset = DpOffset(12.dp, 0.dp),
                ),
            modifier =
                Modifier
                    .wrapContentSize()
                    .align(Alignment.Center),
        ) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                modifier = Modifier.size(16.dp),
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
        isSelected = false,
        onClick = {},
    )
}
