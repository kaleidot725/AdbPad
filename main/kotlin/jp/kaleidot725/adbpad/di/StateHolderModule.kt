package jp.kaleidot725.adbpad.di

import jp.kaleidot725.adbpad.ui.screen.command.CommandStateHolder
import jp.kaleidot725.adbpad.ui.screen.device.DeviceSettingsStateHolder
import jp.kaleidot725.adbpad.ui.screen.main.MainStateHolder
import jp.kaleidot725.adbpad.ui.screen.screenshot.ScreenshotStateHolder
import jp.kaleidot725.adbpad.ui.screen.setting.SettingStateHolder
import jp.kaleidot725.adbpad.ui.screen.text.TextCommandStateHolder
import jp.kaleidot725.adbpad.ui.section.right.RightStateHolder
import jp.kaleidot725.adbpad.ui.section.top.TopStateHolder
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
                textCommandRepository = get(),
                getTextCommandUseCase = get(),
                executeTextCommandUseCase = get(),
                getSelectedDeviceFlowUseCase = get(),
            )
        }

        factory {
            ScreenshotStateHolder(
                takeScreenshotUseCase = get(),
                getScreenshotCommandUseCase = get(),
                getSelectedDeviceFlowUseCase = get(),
                screenshotCommandRepository = get(),
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
                getScrcpySettingsUseCase = get(),
                saveScrcpySettingsUseCase = get(),
                restartAdbUseCase = get(),
                getAccentColorUseCase = get(),
                saveAccentColorUseCase = get(),
            )
        }

        factory {
            TopStateHolder(
                updateDevicesUseCase = get(),
                getSelectedDeviceFlowUseCase = get(),
                selectDeviceUseCase = get(),
                executeDeviceControlCommandUseCase = get(),
                launchScrcpyUseCase = get(),
            )
        }

        factory {
            RightStateHolder(
                getSelectedDeviceFlowUseCase = get(),
                executeDeviceControlCommandUseCase = get(),
                launchScrcpyUseCase = get(),
            )
        }

        factory {
            DeviceSettingsStateHolder(
                getSelectedDeviceFlowUseCase = get(),
                deviceSettingsRepository = get(),
            )
        }

        factory {
            MainStateHolder(
                commandStateHolder = get(),
                textCommandStateHolder = get(),
                screenshotStateHolder = get(),
                getWindowSizeUseCase = get(),
                saveWindowSizeUseCase = get(),
                startAdbUseCase = get(),
                getDarkModeFlowUseCase = get(),
                getLanguageUseCase = get(),
                getAccentColorUseCase = get(),
                refreshUseCase = get(),
                topStateHolder = get(),
                rightStateHolder = get(),
                deviceSettingsStateHolder = get(),
                settingStateHolder = get(),
                shutdownAppUseCase = get(),
            )
        }
    }
