package jp.kaleidot725.adbpad

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.window.application
import jp.kaleidot725.adbpad.core.mvi.MVIRootContent
import jp.kaleidot725.adbpad.di.domainModule
import jp.kaleidot725.adbpad.di.repositoryModule
import jp.kaleidot725.adbpad.di.stateHolderModule
import jp.kaleidot725.adbpad.domain.model.setting.WindowSize
import jp.kaleidot725.adbpad.ui.screen.main.MainScreen
import jp.kaleidot725.adbpad.ui.screen.main.MainStateHolder
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin

fun main() {
    startKoin { modules(repositoryModule, domainModule, stateHolderModule) }
    application {
        val mainStateHolder by remember { mutableStateOf(GlobalContext.get().get<MainStateHolder>()) }
        MVIRootContent(mvi = mainStateHolder) { state, onAction ->
            if (state.size == WindowSize.UNKNOWN) return@MVIRootContent
            if (state.isDark == null) return@MVIRootContent
            MainScreen(
                state = state,
                onAction = onAction,
                mainStateHolder = mainStateHolder,
                onExitApplication = { exitApplication() },
            )
        }
    }
}
