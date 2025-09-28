package jp.kaleidot725.adbpad.ui.component.explorer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.OpenInBrowser
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun DataFileItem(
    dataFile: DataFile,
    openBookmark: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = dataFile.name,
            maxLines = 1,
            fontSize = 20.sp,
            overflow = TextOverflow.Ellipsis,
            modifier =
                Modifier
                    .weight(1f, fill = true)
                    .align(Alignment.CenterVertically),
        )

        Icon(
            imageVector = Icons.Default.OpenInBrowser,
            contentDescription = null,
            modifier =
                Modifier
                    .width(width = 40.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .clickable { openBookmark() },
        )
    }
}
