package jp.kaleidot725.adbpad.ui.screen.version.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun VersionTableHeader(modifier: Modifier = Modifier) {
    Row(modifier) {
        Text(
            text = "ANDROID PLATFORM VERSION",
            style =
                TextStyle(
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold,
                ),
            modifier =
                Modifier
                    .wrapContentHeight()
                    .weight(0.3f)
                    .align(Alignment.CenterVertically)
                    .alignByBaseline()
                    .padding(4.dp),
        )

        Text(
            text = "API LEVEL",
            style =
                TextStyle(
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold,
                ),
            modifier =
                Modifier
                    .wrapContentHeight()
                    .weight(0.3f)
                    .align(Alignment.CenterVertically)
                    .alignByBaseline()
                    .padding(4.dp),
        )

        Text(
            text = "CUMULATIVE DISTRIBUTION",
            style =
                TextStyle(
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold,
                ),
            modifier =
                Modifier
                    .wrapContentHeight()
                    .weight(0.3f)
                    .align(Alignment.CenterVertically)
                    .alignByBaseline()
                    .padding(4.dp),
        )
    }
}
