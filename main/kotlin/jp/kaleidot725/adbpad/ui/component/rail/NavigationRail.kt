package jp.kaleidot725.adbpad.ui.component.rail

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.Camera
import com.composables.icons.lucide.ChevronsRight
import com.composables.icons.lucide.File
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.MonitorSmartphone
import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.ui.screen.main.state.MainCategory

@Composable
fun NavigationRail(
    category: MainCategory,
    onSelectCategory: (MainCategory) -> Unit,
) {
    Column(modifier = Modifier.fillMaxHeight().padding(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        NavigationRailItem(
            label = Language.tooltipCommand,
            icon = Lucide.ChevronsRight,
            contentDescription = "device menu",
            isSelected = category == MainCategory.Command,
            onClick = { onSelectCategory(MainCategory.Command) },
        )

        NavigationRailItem(
            label = Language.tooltipText,
            icon = Lucide.File,
            contentDescription = "text menu",
            isSelected = category == MainCategory.Text,
            onClick = { onSelectCategory(MainCategory.Text) },
        )

        NavigationRailItem(
            label = Language.tooltipScreenshot,
            icon = Lucide.Camera,
            contentDescription = "screenshot menu",
            isSelected = category == MainCategory.Screenshot,
            onClick = { onSelectCategory(MainCategory.Screenshot) },
        )

        NavigationRailItem(
            label = Language.tooltipNewDisplay,
            icon = Lucide.MonitorSmartphone,
            contentDescription = "virtual display menu",
            isSelected = category == MainCategory.ScrcpyNewDisplay,
            onClick = { onSelectCategory(MainCategory.ScrcpyNewDisplay) },
        )

        if (false) {
            NavigationRailItem(
                label = Language.tooltipFile,
                icon = Lucide.File,
                contentDescription = "file menu",
                isSelected = category == MainCategory.File,
                onClick = { onSelectCategory(MainCategory.File) },
            )
        }
    }
}

@Preview
@Composable
private fun NavigationRail_Preview() {
    NavigationRail(MainCategory.Text, {})
}
