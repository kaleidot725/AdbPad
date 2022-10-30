package jp.kaleidot725.adbpad

import jp.kaleidot725.adbpad.repository.DeviceRepositoryImpl
import jp.kaleidot725.adbpad.view.screen.command.CommandStateHolder
import jp.kaleidot725.adbpad.view.screen.input.InputTextStateHolder
import jp.kaleidot725.adbpad.view.screen.menu.MenuStateHolder
import jp.kaleidot725.adbpad.view.screen.screenshot.ScreenshotStateHolder
import org.koin.dsl.module

val repositoryModule = module {
    single {
        DeviceRepositoryImpl()
    }
}

val domainModule = module {
    
}

val stateHolderModule = module {
    factory {
        CommandStateHolder(
            getNotRunningCommandList = get(),
            getRunningCommandList = get(),
            executeCommandUseCase = get()
        )
    }

    factory {
        InputTextStateHolder(
            addInputTextUseCase = get(),
            deleteInputTextUseCase = get(),
            getInputTextUseCase = get(),
            executeInputTextCommandUseCase = get()
        )
    }

    factory {
        MenuStateHolder(
            startAdbUseCase = get(),
            getAndroidDevicesFlowUseCase = get(),
            getMenuListUseCase = get()
        )
    }

    factory {
        ScreenshotStateHolder(
            takeScreenshotUseCase = get(),
            takeThemeScreenshotUseCase = get()
        )
    }
}