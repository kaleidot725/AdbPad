package jp.kaleidot725.adbpad.ui.screen.setting.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.ChevronDown
import com.composables.icons.lucide.Lucide
import jp.kaleidot725.adbpad.domain.model.setting.AccentColor
import jp.kaleidot725.adbpad.ui.common.resource.UserColor
import jp.kaleidot725.adbpad.ui.common.resource.clickableBackground
import jp.kaleidot725.adbpad.ui.common.resource.defaultBorder

@Composable
fun AccentColorDropButton(
    accentColors: List<AccentColor>,
    selectedAccentColor: AccentColor,
    onSelect: (AccentColor) -> Unit,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }
    val isLightTheme = MaterialTheme.colorScheme.surface.luminance() > 0.5

    Box(modifier) {
        Row(
            modifier =
                Modifier
                    .width(200.dp)
                    .defaultBorder()
                    .clip(RoundedCornerShape(4.dp))
                    .clickableBackground()
                    .clickable { expanded = true }
                    .padding(vertical = 8.dp, horizontal = 12.dp),
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.align(Alignment.CenterVertically).weight(1.0f),
            ) {
                Box(
                    modifier =
                        Modifier
                            .size(20.dp)
                            .clip(CircleShape)
                            .background(selectedAccentColor.getColor(isLightTheme))
                            .align(Alignment.CenterVertically),
                )

                Text(
                    text = selectedAccentColor.getTitle(),
                    modifier = Modifier.align(Alignment.CenterVertically),
                )
            }

            Icon(
                imageVector = Lucide.ChevronDown,
                contentDescription = null,
                modifier = Modifier.align(Alignment.CenterVertically),
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier =
                Modifier
                    .width(200.dp)
                    .background(
                        color = UserColor.getDropdownBackgroundColor(),
                        shape = RoundedCornerShape(4.dp),
                    )
                    .border(
                        width = 1.dp,
                        color = UserColor.getSplitterColor(),
                        shape = RoundedCornerShape(4.dp),
                    ),
        ) {
            accentColors.forEach { accentColor ->
                DropdownMenuItem(
                    text = {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            Box(
                                modifier =
                                    Modifier
                                        .size(20.dp)
                                        .clip(CircleShape)
                                        .background(accentColor.getColor(isLightTheme))
                                        .align(Alignment.CenterVertically),
                            )

                            Text(
                                text = accentColor.getTitle(),
                                style = MaterialTheme.typography.titleSmall,
                                modifier = Modifier.align(Alignment.CenterVertically),
                            )
                        }
                    },
                    onClick = {
                        expanded = false
                        onSelect(accentColor)
                    },
                )
            }
        }
    }
}
