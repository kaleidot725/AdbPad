package jp.kaleidot725.adbpad.view.component.screenshot

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.domain.model.command.ScreenshotCommand
import jp.kaleidot725.adbpad.view.component.RunningIndicator

@Composable
fun ScreenshotButton(
    selectedCommand: ScreenshotCommand?,
    isCapturing: Boolean,
    onTake: () -> Unit,
    onChangeType: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .width(250.dp)
                .height(35.dp)
                .background(MaterialTheme.colors.primary, RoundedCornerShape(4.dp))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.8f)
                    .clickable { if (!isCapturing) onTake() }
            ) {
                if (isCapturing) {
                    Box(Modifier.align(Alignment.Center)) { RunningIndicator() }
                } else {
                    Text(
                        text = selectedCommand?.title ?: "",
                        color = MaterialTheme.colors.onPrimary,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

            Spacer(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
                    .background(Color.Black.copy(alpha = 0.6f))
            )

            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(50.dp)
                    .background(Color.Black.copy(alpha = 0.3f))
                    .clickable { onChangeType() }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "",
                    tint = MaterialTheme.colors.onPrimary,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Preview
@Composable
private fun ScreenshotButton_Preview() {
    MaterialTheme {
        ScreenshotButton(
            selectedCommand = ScreenshotCommand.Current(false),
            isCapturing = false,
            onTake = {},
            onChangeType = {}
        )
    }
}
