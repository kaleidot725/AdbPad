package jp.kaleidot725.adbpad.ui.di

import jp.kaleidot725.adbpad.MainStateHolder
import jp.kaleidot725.adbpad.ui.screen.command.CommandStateHolder
import jp.kaleidot725.adbpad.ui.screen.screenshot.ScreenshotStateHolder
import jp.kaleidot725.adbpad.ui.screen.setting.SettingStateHolder
import jp.kaleidot725.adbpad.ui.screen.text.TextCommandStateHolder
import org.koin.dsl.module

val stateHolderModule =
    module {
        factory {
            CommandStateHolder(
                getNormalCommandGroup = get(),
                executeCommandUseCase = get(),
                getSelectedDeviceFlowUseCase = get(),
            )
        }

        factory {
            TextCommandStateHolder(
                addTextCommandUseCase = get(),
                deleteTextCommandUseCase = get(),
                getTextCommandUseCase = get(),
                executeTextCommandUseCase = get(),
                getSelectedDeviceFlowUseCase = get(),
                sendUserInputTextCommandUseCase = get(),
                sendTabCommandUseCase = get(),
            )
        }

        factory {
            ScreenshotStateHolder(
                takeScreenshotUseCase = get(),
                getScreenshotCommandUseCase = get(),
                getSelectedDeviceFlowUseCase = get(),
                deleteScreenshotPreviewUseCase = get(),
                copyScreenshotToClipboardUseCase = get(),
            )
        }

        factory {
            SettingStateHolder(
                getSdkPathUseCase = get(),
                saveSdkPathUseCase = get(),
                getAppearanceUseCase = get(),
                saveAppearanceUseCase = get(),
                getLanguageUseCase = get(),
                saveLanguageUseCase = get(),
                restartAdbUseCase = get(),
            )
        }

        factory {
            MainStateHolder(
                commandStateHolder = get(),
                textCommandStateHolder = get(),
                screenshotStateHolder = get(),
                getEventFlowUseCase = get(),
                getWindowSizeUseCase = get(),
                saveWindowSizeUseCase = get(),
                startAdbUseCase = get(),
                getDarkModeFlowUseCase = get(),
                getLanguageUseCase = get(),
                refreshUseCase = get(),
                getSelectedDeviceFlowUseCase = get(),
                updateDevicesUseCase = get(),
                selectDeviceUseCase = get(),
            )
        }
    }
