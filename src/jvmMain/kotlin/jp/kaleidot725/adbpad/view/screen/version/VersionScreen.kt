package jp.kaleidot725.adbpad.view.screen.version

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
                )
            }
        }
    }
}
