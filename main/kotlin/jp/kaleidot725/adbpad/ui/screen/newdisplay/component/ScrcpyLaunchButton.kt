package jp.kaleidot725.adbpad.ui.screen.newdisplay.component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Play
import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.ui.component.indicator.RunningIndicator

@Composable
fun ScrcpyLaunchButton(
    isLaunching: Boolean,
    canLaunch: Boolean,
    onLaunch: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onLaunch,
        enabled = canLaunch && !isLaunching,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        modifier = modifier,
    ) {
        if (isLaunching) {
            Box { RunningIndicator(modifier = Modifier.size(16.dp)) }
        } else {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Icon(
                    imageVector = Lucide.Play,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                )
                Text(text = Language.execute)
            }
        }
    }
}

@Preview
@Composable
private fun ScrcpyLaunchButtonPreview() {
    ScrcpyLaunchButton(
        isLaunching = false,
        canLaunch = true,
        onLaunch = {},
    )
}
