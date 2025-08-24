package jp.kaleidot725.adbpad.ui.screen.device.component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.ui.common.resource.UserColor

@Composable
fun DeviceHeader(
    idWeight: Float = 0.5f,
    nameWeight: Float = 1.0f,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier =
            modifier
                .background(UserColor.getContentBackgroundColor(), RoundedCornerShape(8.dp))
                .padding(horizontal = 12.dp, vertical = 8.dp),
    ) {
        Text(
            text = "ID",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(idWeight),
        )
        Text(
            text = "NAME",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(nameWeight),
        )
    }
}

@Preview
@Composable
private fun Preview() {
    DeviceHeader()
}
