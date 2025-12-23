package jp.kaleidot725.adbpad.ui.screen.error

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.ui.component.button.FloatingDialog

@Composable
fun AdbErrorScreen(onOpenSetting: () -> Unit) {
    FloatingDialog(onDismiss = {}, modifier = Modifier.width(400.dp).wrapContentHeight()) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(8.dp),
        ) {
            Text(
                text = Language.adbErrorTitle,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 4.dp),
            )

            HorizontalDivider(modifier = Modifier.fillMaxWidth())

            Text(
                text = Language.adbErrorMessage,
                style = MaterialTheme.typography.titleSmall,
                maxLines = 2,
                modifier = Modifier.padding(horizontal = 4.dp),
            )

            Button(
                onClick = onOpenSetting,
                modifier = Modifier.align(Alignment.End),
            ) {
                Text(
                    text = Language.adbErrorOpenSetting,
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.wrapContentWidth(),
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}
