// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import jp.kaleidot725.adbpad.MainStateHolder
import jp.kaleidot725.adbpad.domain.di.domainModule
import jp.kaleidot725.adbpad.domain.model.Dialog
import jp.kaleidot725.adbpad.domain.model.Event
import jp.kaleidot725.adbpad.domain.model.Language
import jp.kaleidot725.adbpad.domain.model.Menu
import jp.kaleidot725.adbpad.domain.model.setting.WindowSize
import jp.kaleidot725.adbpad.domain.model.setting.getWindowSize
import jp.kaleidot725.adbpad.repository.di.repositoryModule
import jp.kaleidot725.adbpad.view.common.resource.AppTheme
import jp.kaleidot725.adbpad.view.di.stateHolderModule
import jp.kaleidot725.adbpad.view.screen.CommandScreen
import jp.kaleidot725.adbpad.view.screen.MenuScreen
import jp.kaleidot725.adbpad.view.screen.ScreenLayout
import jp.kaleidot725.adbpad.view.screen.ScreenshotScreen
import jp.kaleidot725.adbpad.view.screen.setting.SettingScreen
import jp.kaleidot725.adbpad.view.screen.setting.SettingStateHolder
import jp.kaleidot725.adbpad.view.screen.text.TextCommandScreen
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin

fun main() {
    startKoin {
        modules(repositoryModule, domainModule, stateHolderModule)
    }

    application {
        val mainStateHolder by remember { mutableStateOf(GlobalContext.get().get<MainStateHolder>()) }
        val event by mainStateHolder.event.collectAsState(Event.NULL)
        val state by mainStateHolder.state.collectAsState()

        if (state.size == WindowSize.UNKNOWN) {
            return@application
        }

        val windowState by remember(state.size) {
            derivedStateOf { WindowState(width = state.size.width.dp, height = state.size.height.dp) }
        }

        Window(title = Language.WINDOW_TITLE, onCloseRequest = ::exitApplication, state = windowState) {
            AppTheme {
                val menuStateHolder = mainStateHolder.menuStateHolder
                val menuState by menuStateHolder.state.collectAsState()

                val commandStateHolder = mainStateHolder.commandStateHolder
                val commandState by commandStateHolder.state.collectAsState()

                val inputTextStateHolder = mainStateHolder.textCommandStateHolder
                val inputTextState by inputTextStateHolder.state.collectAsState()

                val screenshotStateHolder = mainStateHolder.screenshotStateHolder
                val screenshotState by screenshotStateHolder.state.collectAsState()

                val frameWindowScope = this
                DisposableEffect(mainStateHolder) {
                    mainStateHolder.setup()
                    onDispose {
                        mainStateHolder.saveSetting(frameWindowScope.getWindowSize())
                        mainStateHolder.dispose()
                    }
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
                            onShowSetting = { mainStateHolder.openSetting() },
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
                                    preview = screenshotState.preview,
                                    isCapturing = screenshotState.isCapturing,
                                    commands = screenshotState.commands,
                                    onTakeScreenshot = { screenshot -> screenshotStateHolder.takeScreenShot(screenshot) }
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
                        when (state.dialog) {
                            Dialog.Setting -> {
                                val settingStateHolder by remember {
                                    mutableStateOf(GlobalContext.get().get<SettingStateHolder>())
                                }
                                val settingState by settingStateHolder.state.collectAsState()

                                DisposableEffect(mainStateHolder) {
                                    settingStateHolder.setup()
                                    onDispose { settingStateHolder.dispose() }
                                }

                                SettingScreen(
                                    adbDirectoryPath = settingState.adbDirectoryPath,
                                    onChangeAdbDirectoryPath = settingStateHolder::updateAdbDirectoryPath,
                                    isValidAdbDirectoryPath = settingState.isValidAdbDirectoryPath,
                                    adbPortNumber = settingState.adbPortNumber,
                                    onChangeAdbPortNumber = settingStateHolder::updateAdbPortNumberPath,
                                    isValidAdbPortNumber = settingState.isValidAdbPortNumber,
                                    sdkAndroidDirectoryPath = settingState.sdkAndroidDirectoryPath,
                                    onChangeSdkAndroidDirectoryPath = settingStateHolder::updateAndroidSdkDirectoryPath,
                                    isValidSdkAndroidDirectoryPath = settingState.isValidSdkAndroidDirectoryPath,
                                    onSave = settingStateHolder::save,
                                    canSave = settingState.canSave,
                                    onCancel = settingStateHolder::cancel,
                                    onClose = { mainStateHolder.closeSetting() }
                                )
                            }

                            Dialog.AdbError -> {
                                Text("ERROR", Modifier.size(50.dp).background(Color.Red))
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
