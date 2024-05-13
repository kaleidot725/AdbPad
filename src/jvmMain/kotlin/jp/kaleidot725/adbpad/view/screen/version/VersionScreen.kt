package jp.kaleidot725.adbpad.view.screen.version

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jp.kaleidot725.adbpad.domain.model.version.Version
import jp.kaleidot725.adbpad.view.component.AutoSizableText
import jp.kaleidot725.adbpad.view.screen.version.component.VersionTable
import kotlin.math.round

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
                CircularProgressIndicator()
            }
            else -> {
                VersionTable(
                    versions = state.versions,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
