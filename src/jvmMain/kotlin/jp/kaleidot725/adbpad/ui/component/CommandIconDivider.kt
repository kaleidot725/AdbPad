package jp.kaleidot725.adbpad.ui.component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Power
import jp.kaleidot725.adbpad.ui.common.resource.clickableBackground
import org.checkerframework.checker.units.qual.degrees

@Composable
fun CommandIconDivider(
    modifier: Modifier = Modifier,
) {
    Divider(
        modifier = modifier
            .width(9.dp)
            .fillMaxHeight()
            .padding(vertical = 6.dp, horizontal = 4.dp)
            .background(MaterialTheme.colors.onBackground)
    )
}

@Preview
@Composable
private fun Preview() {
    CommandIconDivider()
}