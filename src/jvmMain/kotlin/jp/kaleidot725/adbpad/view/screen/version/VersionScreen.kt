package jp.kaleidot725.adbpad.view.screen.version

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.domain.model.UserColor
import jp.kaleidot725.adbpad.view.screen.version.component.VersionTable

@Composable
fun VersionScreen(
    state: VersionState,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        when {
            state.hasError -> {
                Button(
                    onClick = onRetry,
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Text(text = "Retry")
                }
            }

            state.isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            else -> {
                VersionTable(
                    versions = state.versions,
                    modifier = Modifier.fillMaxSize()
                        .padding(16.dp)
                        .border(
                            border = BorderStroke(1.dp, UserColor.getSplitterColor()),
                            shape = RoundedCornerShape(4.dp),
                        )
                        .background(
                            color = Color.DarkGray,
                            shape = RoundedCornerShape(4.dp)
                        ),
                )
            }
        }
    }
}
