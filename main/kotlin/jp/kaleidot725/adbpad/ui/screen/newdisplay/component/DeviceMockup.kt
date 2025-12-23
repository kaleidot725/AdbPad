package jp.kaleidot725.adbpad.ui.screen.newdisplay.component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.isFinite
import kotlin.math.max
import kotlin.math.min

@Composable
fun DeviceMockup(
    width: Int,
    height: Int,
    maxDimension: Float,
    modifier: Modifier = Modifier,
) {
    val bodyShape = MaterialTheme.shapes.large
    val screenShape = MaterialTheme.shapes.medium
    val frameColor = MaterialTheme.colorScheme.surfaceVariant
    val screenColor = MaterialTheme.colorScheme.surface
    val detailColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.15f)

    val maxDimensionSafe = max(maxDimension, 1f)
    val widthPx = width.coerceAtLeast(1)
    val heightPx = height.coerceAtLeast(1)

    BoxWithConstraints(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        val maxWidthDp = maxWidth
        val maxHeightDp = if (maxHeight.isFinite) maxHeight else 380.dp

        val longestSide = max(widthPx, heightPx).toFloat()
        val relativeScale = (longestSide / maxDimensionSafe).coerceIn(0.25f, 1f)
        val boundingWidthDp = maxWidthDp * relativeScale
        val boundingHeightDp = maxHeightDp * relativeScale

        val widthScale = boundingWidthDp.value / widthPx
        val heightScale = boundingHeightDp.value / heightPx
        val scale = min(widthScale, heightScale)

        val deviceWidthDp = (widthPx * scale).dp
        val deviceHeightDp = (heightPx * scale).dp

        Box(
            modifier =
                Modifier
                    .size(deviceWidthDp, deviceHeightDp)
                    .clip(bodyShape)
                    .background(frameColor)
                    .border(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.4f), bodyShape)
                    .padding(16.dp),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier =
                        Modifier
                            .width(72.dp * relativeScale)
                            .height(6.dp * relativeScale)
                            .clip(RoundedCornerShape(3.dp))
                            .background(detailColor),
                )

                Box(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .clip(screenShape)
                            .background(screenColor)
                            .border(1.dp, detailColor, screenShape),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "$height × $width",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp * relativeScale)) {
                    Box(
                        modifier =
                            Modifier
                                .width(32.dp * relativeScale)
                                .height(6.dp * relativeScale)
                                .clip(RoundedCornerShape(3.dp))
                                .background(detailColor),
                    )
                    Box(
                        modifier =
                            Modifier
                                .width(16.dp * relativeScale)
                                .height(6.dp * relativeScale)
                                .clip(RoundedCornerShape(3.dp))
                                .background(detailColor),
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun DeviceMockupPreview() {
    DeviceMockup(
        width = 1080,
        height = 1920,
        maxDimension = 1920f,
    )
}
