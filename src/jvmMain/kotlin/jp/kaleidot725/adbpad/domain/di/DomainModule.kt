package jp.kaleidot725.adbpad.domain.di

import jp.kaleidot725.adbpad.domain.usecase.adb.StartAdbUseCase
import jp.kaleidot725.adbpad.domain.usecase.command.ExecuteCommandUseCase
import jp.kaleidot725.adbpad.domain.usecase.command.GetCommandList
import jp.kaleidot725.adbpad.domain.usecase.device.GetDevicesFlowUseCase
import jp.kaleidot725.adbpad.domain.usecase.device.GetSelectedDeviceFlowUseCase
import jp.kaleidot725.adbpad.domain.usecase.device.SelectDeviceUseCase
import jp.kaleidot725.adbpad.domain.usecase.event.GetEventFlowUseCase
import jp.kaleidot725.adbpad.domain.usecase.menu.GetMenuListUseCase
import jp.kaleidot725.adbpad.domain.usecase.screenshot.GetScreenshotCommandUseCase
import jp.kaleidot725.adbpad.domain.usecase.screenshot.GetScreenshotPreviewUseCase
import jp.kaleidot725.adbpad.domain.usecase.screenshot.TakeScreenshotUseCase
import jp.kaleidot725.adbpad.domain.usecase.text.AddTextCommandUseCase
import jp.kaleidot725.adbpad.domain.usecase.text.DeleteTextCommandUseCase
import jp.kaleidot725.adbpad.domain.usecase.text.ExecuteTextCommandUseCase
import jp.kaleidot725.adbpad.domain.usecase.text.GetTextCommandUseCase
import jp.kaleidot725.adbpad.domain.usecase.text.SendUserInputTextCommandUseCase
import org.koin.dsl.module

val domainModule = module {
    factory {
        StartAdbUseCase()
    }
    factory {
        ExecuteCommandUseCase(get(), get())
    }
    factory {
        GetCommandList(get())
    }
    factory {
        GetDevicesFlowUseCase(get())
    }
    factory {
        GetSelectedDeviceFlowUseCase(get())
    }
    factory {
        SelectDeviceUseCase(get())
    }
    factory {
        AddTextCommandUseCase(get())
    }
    factory {
        DeleteTextCommandUseCase(get())
    }
    factory {
        ExecuteTextCommandUseCase(get(), get())
    }
    factory {
        GetTextCommandUseCase(get())
    }
    factory {
        GetMenuListUseCase()
    }
    factory {
        TakeScreenshotUseCase(get(), get())
    }
    factory {
        GetScreenshotCommandUseCase(get())
    }
    factory {
        GetEventFlowUseCase(get())
    }
    factory {
        SendUserInputTextCommandUseCase(get(), get())
    }
    factory {
        GetScreenshotPreviewUseCase(get())
    }
}
