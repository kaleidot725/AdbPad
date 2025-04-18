package jp.kaleidot725.adbpad.ui.component.rail

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.Camera
import com.composables.icons.lucide.ChevronsRight
import com.composables.icons.lucide.Command
import com.composables.icons.lucide.File
import com.composables.icons.lucide.FileText
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Settings
import com.composables.icons.lucide.Text
import jp.kaleidot725.adbpad.MainCategory
import jp.kaleidot725.adbpad.domain.model.language.Language

@Composable
fun NavigationRail(
    category: MainCategory,
    onSelectCategory: (MainCategory) -> Unit,
    onOpenSetting: () -> Unit,
) {
    Column(modifier = Modifier.padding(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        NavigationRailItem(
            label = Language.tooltipCommand,
            icon = Lucide.ChevronsRight,
            contentDescription = "device menu",
            isSelected = category == MainCategory.Command,
            onClick = { onSelectCategory(MainCategory.Command) },
        )

        NavigationRailItem(
            label = Language.tooltipText,
            icon = Lucide.FileText,
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

        if (false) {
            NavigationRailItem(
                label = Language.tooltipFile,
                icon = Lucide.File,
                contentDescription = "file menu",
                isSelected = category == MainCategory.File,
                onClick = { onSelectCategory(MainCategory.File) },
            )
        }

        Spacer(Modifier.weight(1.0f))

        NavigationRailItem(
            label = Language.tooltipSetting,
            icon = Lucide.Settings,
            contentDescription = "device menu",
            isSelected = false,
            onClick = onOpenSetting,
        )
    }
}

@Preview
@Composable
private fun NavigationRail_Preview() {
    NavigationRail(MainCategory.Text, {}, {})
}
