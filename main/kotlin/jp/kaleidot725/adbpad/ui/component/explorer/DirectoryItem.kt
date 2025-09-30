package jp.kaleidot725.adbpad.ui.component.explorer

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.domain.model.explorer.ExplorerDirectory

@Composable
internal fun DirectoryItem(
    directory: ExplorerDirectory,
    isExpanded: Boolean,
    onExpand: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        val degrees: Float by animateFloatAsState(if (isExpanded) 90f else 0f)

        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = null,
            modifier =
                Modifier
                    .size(24.dp)
                    .rotate(degrees)
                    .align(Alignment.CenterVertically)
                    .clip(CircleShape)
                    .clickable { onExpand.invoke() },
        )

        Text(
            text = directory.name,
            maxLines = 1,
            style = MaterialTheme.typography.bodyLarge,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.align(Alignment.CenterVertically).weight(1.0f),
        )

        Text(
            text = directory.list.count().toString(),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier =
                Modifier
                    .width(width = 40.dp)
                    .align(Alignment.CenterVertically),
        )
    }
}
