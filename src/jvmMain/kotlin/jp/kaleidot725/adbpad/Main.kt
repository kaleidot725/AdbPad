// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import jp.kaleidot725.adbpad.MainStateHolder
import jp.kaleidot725.adbpad.domain.model.Dialog
import jp.kaleidot725.adbpad.domain.model.Event
import jp.kaleidot725.adbpad.domain.model.Menu
import jp.kaleidot725.adbpad.view.common.resource.AppTheme
import jp.kaleidot725.adbpad.view.common.resource.StringRes
import jp.kaleidot725.adbpad.view.screen.CommandScreen
import jp.kaleidot725.adbpad.view.screen.InputTextScreen
import jp.kaleidot725.adbpad.view.screen.MenuScreen
import jp.kaleidot725.adbpad.view.screen.ScreenLayout
import jp.kaleidot725.adbpad.view.screen.ScreenshotScreen

fun main() = application {
    Window(title = StringRes.WINDOW_TITLE, onCloseRequest = ::exitApplication) {
        AppTheme {
            var dialog by remember { mutableStateOf<Dialog?>(null) }
            val mainStateHolder by remember { mutableStateOf(MainStateHolder()) }
            val event by mainStateHolder.event.collectAsState(Event.NULL_EVENT)

            val menuStateHolder = mainStateHolder.state.menuStateHolder
            val menuState by menuStateHolder.state.collectAsState()

            val commandStateHolder = mainStateHolder.state.commandStateHolder
            val commandState by commandStateHolder.state.collectAsState()

            val inputTextStateHolder = mainStateHolder.state.inputTextStateHolder
            val inputTextState by inputTextStateHolder.state.collectAsState()

            val screenshotStateHolder = mainStateHolder.state.screenshotStateHolder
            val screenshotState by screenshotStateHolder.state.collectAsState()

            DisposableEffect(mainStateHolder) {
                mainStateHolder.setup()
                onDispose { mainStateHolder.dispose() }
            }

            ScreenLayout(
                leftPane = {
                    MenuScreen(
                        devices = menuState.devices,
                        selectedDevice = menuState.selectedDevice,
                        onSelectDevice = { menuStateHolder.selectDevice(it) },
                        menus = menuState.menus,
                        selectedMenu = menuState.selectedMenu,
                        onSelectMenu = { menuStateHolder.selectMenu(it) },
                        onShowSetting = { dialog = Dialog.Setting },
                        modifier = Modifier
                            .width(250.dp)
                            .fillMaxHeight()
                            .padding(horizontal = 12.dp, vertical = 16.dp)
                    )
                },
                rightPane = {
                    when (menuState.selectedMenu) {
                        Menu.Command -> {
                            CommandScreen(
                                commands = commandState.commands,
                                onExecute = { command ->
                                    menuState.selectedDevice?.let { device ->
                                        commandStateHolder.executeCommand(device, command)
                                    }
                                }
                            )
                        }

                        Menu.InputText -> {
                            InputTextScreen(
                                inputText = inputTextState.userInputText,
                                onTextChange = { text ->
                                    inputTextStateHolder.updateInputText(text)
                                },
                                inputTexts = inputTextState.inputTexts,
                                onSend = { text ->
                                    menuState.selectedDevice?.let { device ->
                                        inputTextStateHolder.sendInputText(device, text)
                                    }
                                },
                                canSend = inputTextState.canSendUserInputText,
                                onSave = { text ->
                                    inputTextStateHolder.saveInputText(text)
                                },
                                canSave = inputTextState.canSaveUserInputText,
                                onDelete = { text ->
                                    inputTextStateHolder.deleteInputText(text)
                                }
                            )
                        }

                        Menu.Screenshot -> {
                            ScreenshotScreen(
                                image1 = screenshotState.imageFile1,
                                image2 = screenshotState.imageFile2,
                                onTakeScreenShot = {
                                    menuState.selectedDevice?.let {
                                        screenshotStateHolder.takeScreenShot(it)
                                    }
                                },
                                onTakeThemeScreenshot = {
                                    menuState.selectedDevice?.let {
                                        screenshotStateHolder.takeThemeScreenShot(it)
                                    }
                                }
                            )
                        }

                        null -> Unit
                    }
                },
                notificationArea = {
                    Box(Modifier.fillMaxWidth().height(25.dp).padding(horizontal = 8.dp, vertical = 4.dp)) {
                        Text(event.message, style = MaterialTheme.typography.caption)
                    }
                },
                dialog = {
                    when (dialog) {
                        Dialog.Setting -> {
                            Box(Modifier.background(Color.DarkGray.copy(alpha = 0.5f))) {
                                Card(Modifier.fillMaxSize().padding(32.dp)) {
                                    Button(onClick = { dialog = null }) {
                                        Text("Close")
                                    }
                                }
                            }
                        }

                        null -> Unit
                    }
                },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
