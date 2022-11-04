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
import androidx.compose.runtime.LaunchedEffect
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
import jp.kaleidot725.adbpad.domain.di.domainModule
import jp.kaleidot725.adbpad.domain.model.Dialog
import jp.kaleidot725.adbpad.domain.model.Event
import jp.kaleidot725.adbpad.domain.model.Language
import jp.kaleidot725.adbpad.domain.model.Menu
import jp.kaleidot725.adbpad.domain.usecase.adb.StartAdbUseCase
import jp.kaleidot725.adbpad.repository.di.repositoryModule
import jp.kaleidot725.adbpad.view.common.resource.AppTheme
import jp.kaleidot725.adbpad.view.di.stateHolderModule
import jp.kaleidot725.adbpad.view.screen.CommandScreen
import jp.kaleidot725.adbpad.view.screen.MenuScreen
import jp.kaleidot725.adbpad.view.screen.ScreenLayout
import jp.kaleidot725.adbpad.view.screen.ScreenshotScreen
import jp.kaleidot725.adbpad.view.screen.text.TextCommandScreen
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin

fun main() {
    startKoin {
        modules(repositoryModule, domainModule, stateHolderModule)
    }

    application {
        Window(title = Language.WINDOW_TITLE, onCloseRequest = ::exitApplication) {
            LaunchedEffect(Unit) {
                val startAdbUseCase = GlobalContext.get().get<StartAdbUseCase>()
                startAdbUseCase()
            }

            AppTheme {
                var dialog by remember { mutableStateOf<Dialog?>(null) }
                val mainStateHolder by remember { mutableStateOf(GlobalContext.get().get<MainStateHolder>()) }
                val event by mainStateHolder.event.collectAsState(Event.NULL)

                val menuStateHolder = mainStateHolder.menuStateHolder
                val menuState by menuStateHolder.state.collectAsState()

                val commandStateHolder = mainStateHolder.commandStateHolder
                val commandState by commandStateHolder.state.collectAsState()

                val inputTextStateHolder = mainStateHolder.textCommandStateHolder
                val inputTextState by inputTextStateHolder.state.collectAsState()

                val screenshotStateHolder = mainStateHolder.screenshotStateHolder
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
                                    canExecute = commandState.canExecuteCommand,
                                    onExecute = { command ->
                                        commandStateHolder.executeCommand(command)
                                    }
                                )
                            }

                            Menu.InputText -> {
                                TextCommandScreen(
                                    // InputText
                                    inputText = inputTextState.userInputText,
                                    onTextChange = { text ->
                                        inputTextStateHolder.updateInputText(text)
                                    },
                                    isSendingInputText = inputTextState.isSendingUserInputText,
                                    onSendInputText = {
                                        inputTextStateHolder.sendInputText()
                                    },
                                    canSendInputText = inputTextState.canSendInputText,
                                    onSaveInputText = {
                                        inputTextStateHolder.saveInputText()
                                    },
                                    canSaveInputText = inputTextState.canSaveInputText,

                                    // Commands
                                    commands = inputTextState.commands,
                                    onSendCommand = { text ->
                                        inputTextStateHolder.sendCommand(text)
                                    },
                                    canSendCommand = inputTextState.canSendCommand,
                                    onDeleteCommand = { text ->
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
                            Text(
                                text = event.message,
                                color = when (event.level) {
                                    Event.Level.INFO -> Color.Black
                                    Event.Level.WARN -> Color.Yellow
                                    Event.Level.ERROR -> Color.Red
                                },
                                style = MaterialTheme.typography.caption
                            )
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
}
