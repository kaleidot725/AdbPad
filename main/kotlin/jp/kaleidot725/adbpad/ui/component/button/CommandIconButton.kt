package jp.kaleidot725.adbpad.ui.component.button

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Power
import jp.kaleidot725.adbpad.ui.common.resource.clickableBackground

@Composable
fun CommandIconButton(
    modifier: Modifier = Modifier,
    image: ImageVector,
    onClick: () -> Unit,
    degrees: Float = 0f,
    padding: Dp = 0.dp,
) {
    Box(
        modifier =
            modifier
                .size(28.dp)
                .clip(RoundedCornerShape(4.dp))
                .clickableBackground(isDarker = true)
                .clickable { onClick() }
                .padding(4.dp + padding),
    ) {
        Icon(
            imageVector = image,
            tint = MaterialTheme.colorScheme.onBackground,
            contentDescription = null,
            modifier = Modifier.align(Alignment.Center).rotate(degrees),
        )
    }
}

@Preview
@Composable
private fun Preview() {
    CommandIconButton(
        image = Lucide.Power,
        onClick = {},
    )
}
