// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import jp.kaleidot725.adbpad.MainStateHolder
import jp.kaleidot725.adbpad.view.component.menu.MenuPane
import jp.kaleidot725.adbpad.view.layout.AppLayout
import jp.kaleidot725.adbpad.view.page.AutoFillPane
import jp.kaleidot725.adbpad.view.page.CommandPane
import jp.kaleidot725.adbpad.view.page.ScreenShotPane
import jp.kaleidot725.adbpad.view.resource.AppTheme
import jp.kaleidot725.adbpad.view.resource.Menu
import jp.kaleidot725.adbpad.view.resource.WINDOW_TITLE

fun main() = application {
    Window(title = WINDOW_TITLE, onCloseRequest = ::exitApplication) {
        val stateHolder by remember { mutableStateOf(MainStateHolder()) }
        val state by stateHolder.state.collectAsState()

        DisposableEffect(stateHolder) {
            stateHolder.setup()
            onDispose { stateHolder.dispose() }
        }

        AppTheme {
            AppLayout(
                leftPane = {
                    MenuPane(
                        devices = state.devices,
                        selectedDevice = state.selectedDevice,
                        onSelectDevice = { stateHolder.selectDevice(it) },
                        menus = state.menus,
                        selectedMenu = state.selectedMenu,
                        onSelectMenu = { stateHolder.selectMenu(it) },
                        modifier = Modifier
                            .width(250.dp)
                            .fillMaxHeight()
                            .padding(horizontal = 12.dp, vertical = 16.dp)
                    )
                },
                rightPane = {
                    when (state.selectedMenu) {
                        Menu.COMMAND_MENU -> CommandPane(
                            commands = state.commands,
                            onExecute = { stateHolder.executeCommand(it) }
                        )

                        Menu.AUTOFILL_MENU -> AutoFillPane(
                            texts = state.inputTexts,
                            onExecute = { stateHolder.inputText(it) }
                        )

                        Menu.SCREENSHOT_MENU -> ScreenShotPane(
                            image1 = "TEST1",
                            image2 = "TEST2",
                            onTakeScreenShot = { stateHolder.takeScreenShot() },
                            onTakeThemeScreenshot = { stateHolder.takeThemeScreenShot() }
                        )
                    }
                },
                dialog = {},
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}