package jp.kaleidot725.adbpad.ui.component.divider

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CommandIconDivider(modifier: Modifier = Modifier) {
    HorizontalDivider(
        modifier =
            modifier
                .height(9.dp)
                .fillMaxWidth()
                .padding(vertical = 6.dp, horizontal = 4.dp)
                .background(MaterialTheme.colorScheme.onBackground),
    )
}

@Preview
@Composable
private fun Preview() {
    CommandIconDivider()
}
