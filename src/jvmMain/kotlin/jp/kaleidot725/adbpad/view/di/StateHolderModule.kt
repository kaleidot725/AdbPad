package jp.kaleidot725.adbpad.view.di

import jp.kaleidot725.adbpad.MainStateHolder
import jp.kaleidot725.adbpad.view.screen.command.CommandStateHolder
import jp.kaleidot725.adbpad.view.screen.menu.MenuStateHolder
import jp.kaleidot725.adbpad.view.screen.screenshot.ScreenshotStateHolder
import jp.kaleidot725.adbpad.view.screen.text.TextCommandStateHolder
import org.koin.dsl.module

val stateHolderModule = module {
    factory {
        CommandStateHolder(
            getCommandList = get(),
            executeCommandUseCase = get(),
            getSelectedDeviceFlowUseCase = get()
        )
    }

    factory {
        TextCommandStateHolder(
            addTextCommandUseCase = get(),
            deleteTextCommandUseCase = get(),
            getTextCommandUseCase = get(),
            executeTextCommandUseCase = get(),
            getSelectedDeviceFlowUseCase = get()
        )
    }

    factory {
        MenuStateHolder(
            getAndroidDevicesFlowUseCase = get(),
            getMenuListUseCase = get(),
            getSelectedDeviceFlowUseCase = get(),
            selectDeviceUseCase = get()
        )
    }

    factory {
        ScreenshotStateHolder(
            takeScreenshotUseCase = get(),
            takeThemeScreenshotUseCase = get()
        )
    }

    factory {
        MainStateHolder(
            menuStateHolder = get(),
            commandStateHolder = get(),
            textCommandStateHolder = get(),
            screenshotStateHolder = get(),
            getEventFlowUseCase = get()
        )
    }
}
