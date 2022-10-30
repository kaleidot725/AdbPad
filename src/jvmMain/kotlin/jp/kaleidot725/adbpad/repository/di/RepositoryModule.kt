package jp.kaleidot725.adbpad.repository

import jp.kaleidot725.adbpad.domain.repository.DeviceRepository
import jp.kaleidot725.adbpad.repository.impl.DeviceRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<DeviceRepository> {
        DeviceRepositoryImpl()
    }
}
