package jp.kaleidot725.adbpad.ui.screen.version.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import jp.kaleidot725.adbpad.domain.model.version.Version

@Composable
fun VersionTable(
    versions: List<Version>,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        VersionTableHeader(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
        )

        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            versions.forEach { version ->
                VersionTableItem(
                    version = version,
                    color = version.apiLevel.toColor(),
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .weight(version.distributionPercentage.compensatePercentage()),
                )
                Divider(color = Color.White)
            }
        }
    }
}

private fun Double.compensatePercentage(): Float {
    return maxOf(0.05f, this.toFloat())
}

private fun Int.toColor(): Color {
    return when (this) {
        19 -> Color(0xFFc4dac4)
        21 -> Color(0xFF72bf86)
        22 -> Color(0xFF87a9ae)
        23 -> Color(0xFFd9b138)
        24 -> Color(0xFFe15354)
        25 -> Color(0xFF63b8cc)
        26 -> Color(0xFFd38258)
        27 -> Color(0xFFff8725)
        28 -> Color(0xFFe7b528)
        29 -> Color(0xFFc4dac4)
        30 -> Color(0xFF72bf86)
        31 -> Color(0xFF87a9ae)
        33 -> Color(0xFFd9b138)
        else -> Color(0xFFd9b138)
    }
}
