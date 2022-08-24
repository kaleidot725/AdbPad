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
import jp.kaleidot725.adbpad.stateholder.MainStateHolder
import jp.kaleidot725.adbpad.view.component.menu.MenuScreen
import jp.kaleidot725.adbpad.view.page.CommandScreen
import jp.kaleidot725.adbpad.view.page.InputTextScreen
import jp.kaleidot725.adbpad.view.page.ScreenshotScreen
import jp.kaleidot725.adbpad.view.resource.AppTheme
import jp.kaleidot725.adbpad.view.resource.Menu
import jp.kaleidot725.adbpad.view.resource.StringRes
import jp.kaleidot725.adbpad.view.template.ScreenLayout

fun main() = application {
    Window(title = StringRes.WINDOW_TITLE, onCloseRequest = ::exitApplication) {
        val stateHolder by remember { mutableStateOf(MainStateHolder()) }
        val state by stateHolder.state.collectAsState()

        DisposableEffect(stateHolder) {
            stateHolder.setup()
            onDispose { stateHolder.dispose() }
        }

        AppTheme {
            ScreenLayout(
                leftPane = {
                    MenuScreen(
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
                        Menu.COMMAND_MENU -> CommandScreen(
                            commands = state.commands,
                            onExecute = { stateHolder.executeCommand(it) }
                        )

                        Menu.INPUT_TEXT_MENU -> InputTextScreen(
                            inputText = state.inputText,
                            onTextChange = { stateHolder.updateInputText(it) },
                            inputTexts = state.inputTexts,
                            onExecute = { stateHolder.inputText(it) },
                            onSave = { stateHolder.saveInputText(it) },
                            onDelete = { stateHolder.deleteInputText(it) }
                        )

                        Menu.SCREENSHOT_MENU -> ScreenshotScreen(
                            image1 = state.previewImageUrl1,
                            image2 = state.previewImageUrl2,
                            onTakeScreenShot = { stateHolder.takeScreenShot() },
                            onTakeThemeScreenshot = { stateHolder.takeThemeScreenShot() }
                        )
                    }
                },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
