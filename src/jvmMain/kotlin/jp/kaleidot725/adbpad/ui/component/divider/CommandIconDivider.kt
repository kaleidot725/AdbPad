package jp.kaleidot725.adbpad.ui.component.divider

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CommandIconDivider(modifier: Modifier = Modifier) {
    Divider(
        modifier =
            modifier
                .width(9.dp)
                .fillMaxHeight()
                .padding(vertical = 6.dp, horizontal = 4.dp)
                .background(MaterialTheme.colors.onBackground),
    )
}

@Preview
@Composable
private fun Preview() {
    CommandIconDivider()
}
