package jp.kaleidot725.adbpad

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.model.data.Dialog
import jp.kaleidot725.adbpad.model.data.Menu
import jp.kaleidot725.adbpad.view.resource.AppTheme
import jp.kaleidot725.adbpad.view.screen.CommandScreen
import jp.kaleidot725.adbpad.view.screen.InputTextScreen
import jp.kaleidot725.adbpad.view.screen.MenuScreen
import jp.kaleidot725.adbpad.view.screen.ScreenLayout
import jp.kaleidot725.adbpad.view.screen.ScreenshotScreen
import jp.kaleidot725.adbpad.view.screen.command.CommandStateHolder
import jp.kaleidot725.adbpad.view.screen.menu.MenuStateHolder

@Composable
fun MainApp(
    state: MainState,
    onChangeInputText: (String) -> Unit,
    onSendInputText: (String) -> Unit,
    onSaveInputText: (String) -> Unit,
    onDeleteInputText: (String) -> Unit,
    onTakeScreenshot: () -> Unit,
    onTakeThemeScreenshot: () -> Unit,
    onShowSettingDialog: () -> Unit,
    onCloseDialog: () -> Unit
) {
    AppTheme {
        val menuStateHolder by remember { mutableStateOf(MenuStateHolder()) }
        val menuState by menuStateHolder.state.collectAsState()
        DisposableEffect(menuStateHolder) {
            menuStateHolder.setup()
            onDispose { menuStateHolder.dispose() }
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
                    onShowSetting = onShowSettingDialog,
                    modifier = Modifier
                        .width(250.dp)
                        .fillMaxHeight()
                        .padding(horizontal = 12.dp, vertical = 16.dp)
                )
            },
            rightPane = {
                when (menuState.selectedMenu) {
                    Menu.Command -> {
                        val commandStateHolder by remember { mutableStateOf(CommandStateHolder()) }
                        val commandState by commandStateHolder.state.collectAsState()

                        DisposableEffect(commandStateHolder) {
                            commandStateHolder.setup()
                            onDispose { commandStateHolder.dispose() }
                        }
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
                            inputText = state.userInputText,
                            onTextChange = onChangeInputText,
                            inputTexts = state.inputTexts,
                            onSend = onSendInputText,
                            canSend = state.canSendUserInputText,
                            onSave = onSaveInputText,
                            canSave = state.canSaveUserInputText,
                            onDelete = onDeleteInputText
                        )
                    }

                    Menu.Screenshot -> {
                        ScreenshotScreen(
                            image1 = state.imageFile1,
                            image2 = state.imageFile2,
                            onTakeScreenShot = onTakeScreenshot,
                            onTakeThemeScreenshot = onTakeThemeScreenshot
                        )
                    }

                    null -> Unit
                }
            },
            notificationArea = {
                Box(Modifier.fillMaxWidth().height(25.dp).padding(horizontal = 8.dp, vertical = 4.dp)) {
                    Text("Sample text", style = MaterialTheme.typography.caption)
                }
            },
            dialog = {
                when (state.dialog) {
                    Dialog.Setting -> {
                        Box(Modifier.background(Color.DarkGray.copy(alpha = 0.5f))) {
                            Card(Modifier.fillMaxSize().padding(32.dp)) {
                                Button(onClick = { onCloseDialog() }) {
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
