package jp.kaleidot725.adbpad.ui.screen.version.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
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
import jp.kaleidot725.adbpad.ui.component.AutoSizableText
import kotlin.math.round

@Composable
fun VersionTableItem(
    version: Version,
    color: Color,
    modifier: Modifier = Modifier,
) {
    Row(modifier) {
        Spacer(
            modifier =
                Modifier
                    .width(12.dp)
                    .fillMaxHeight()
                    .background(color),
        )

        Box(
            modifier =
                Modifier
                    .weight(0.10f)
                    .fillMaxHeight()
                    .background(color),
        ) {
            AutoSizableText(
                text = "${version.version}",
                style =
                    TextStyle(
                        textAlign = TextAlign.Start,
                        color = Color.Gray,
                    ),
                minFontSize = 8.sp,
                maxFontSize = 16.sp,
                modifier = Modifier.align(Alignment.CenterStart),
            )
        }

        Spacer(
            modifier =
                Modifier
                    .width(4.dp)
                    .fillMaxHeight()
                    .background(color),
        )

        Box(
            modifier =
                Modifier
                    .weight(0.20f)
                    .fillMaxHeight()
                    .background(color),
        ) {
            AutoSizableText(
                text = version.name,
                style =
                    TextStyle(
                        textAlign = TextAlign.Start,
                        color = Color.White,
                        fontWeight = FontWeight.ExtraBold,
                    ),
                minFontSize = 8.sp,
                maxFontSize = 16.sp,
                modifier = Modifier.align(Alignment.CenterStart),
            )
        }

        Box(
            modifier =
                Modifier
                    .weight(0.30f)
                    .fillMaxHeight()
                    .background(color),
        ) {
            AutoSizableText(
                text = "${version.apiLevel}",
                style =
                    TextStyle(
                        textAlign = TextAlign.Start,
                        fontSize = 100.sp,
                        color = Color.Gray,
                    ),
                minFontSize = 8.sp,
                maxFontSize = 40.sp,
                modifier = Modifier.align(Alignment.Center),
            )
        }

        val textCumulativeDistribution =
            if (version.cumulativeDistribution != null) {
                "${version.cumulativeDistribution.format(1)}%"
            } else {
                ""
            }
        Text(
            text = textCumulativeDistribution,
            style =
                TextStyle(
                    textAlign = TextAlign.End,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                ),
            modifier =
                Modifier
                    .weight(0.3f)
                    .fillMaxHeight()
                    .align(Alignment.CenterVertically)
                    .alignByBaseline(),
        )

        Spacer(
            modifier =
                Modifier
                    .fillMaxHeight()
                    .width(12.dp),
        )
    }
}

private fun Double.format(decimals: Int): String {
    var multiplier = 1.0f
    repeat(decimals) { multiplier *= 10 }
    return (round(this * multiplier) / multiplier).toString()
}
