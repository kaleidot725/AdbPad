// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.example.compose.AppTheme
import jp.kaleidot725.adbpad.component.SplitLayout
import jp.kaleidot725.adbpad.resource.WINDOW_TITLE
import jp.kaleidot725.adbpad.view.component.menulist.LeftPane
import jp.kaleidot725.adbpad.view.layout.RightPane

@Composable
@Preview
fun App() {
    AppTheme(useDarkTheme = false) {
        Surface {
            val devices by remember { mutableStateOf(listOf("端末A", "端末B", "端末C")) }
            var selectedDevice by remember { mutableStateOf(devices.first()) }

            val menus by remember { mutableStateOf(listOf("コマンド実行", "スクリーンショット", "自動入力補助")) }
            var selectedMenu by remember { mutableStateOf(menus.first()) }

            SplitLayout(
                leftPane = {
                    LeftPane(
                        devices = devices,
                        selectedDevice = selectedDevice,
                        onSelectDevice = { selectedDevice = it },
                        menus = menus,
                        selectedMenu = selectedMenu,
                        onSelectMenu = { selectedMenu = it },
                        onOpenSetting = { /** TODO */ },
                        modifier = Modifier
                            .width(250.dp)
                            .fillMaxHeight()
                            .padding(horizontal = 12.dp, vertical = 16.dp)
                    )
                },
                rightPane = {
                    RightPane()
                },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

fun main() = application {
    Window(
        title = WINDOW_TITLE,
        onCloseRequest = ::exitApplication
    ) {
        App()
    }
}
