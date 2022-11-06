package jp.kaleidot725.adbpad.repository.di

import jp.kaleidot725.adbpad.domain.repository.DeviceRepository
import jp.kaleidot725.adbpad.domain.repository.EventRepository
import jp.kaleidot725.adbpad.domain.repository.NormalCommandRepository
import jp.kaleidot725.adbpad.domain.repository.ScreenshotCommandRepository
import jp.kaleidot725.adbpad.domain.repository.TextCommandRepository
import jp.kaleidot725.adbpad.repository.impl.DeviceRepositoryImpl
import jp.kaleidot725.adbpad.repository.impl.EventRepositoryImpl
import jp.kaleidot725.adbpad.repository.impl.NormalCommandRepositoryImpl
import jp.kaleidot725.adbpad.repository.impl.ScreenshotCommandRepositoryImpl
import jp.kaleidot725.adbpad.repository.impl.TextCommandRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<EventRepository> {
        EventRepositoryImpl()
    }
    single<DeviceRepository> {
        DeviceRepositoryImpl()
    }
    single<NormalCommandRepository> {
        NormalCommandRepositoryImpl()
    }
    single<TextCommandRepository> {
        TextCommandRepositoryImpl()
    }
    single<ScreenshotCommandRepository> {
        ScreenshotCommandRepositoryImpl()
    }
}
