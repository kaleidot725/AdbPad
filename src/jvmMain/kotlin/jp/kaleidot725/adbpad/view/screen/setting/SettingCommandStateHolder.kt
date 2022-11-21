package jp.kaleidot725.adbpad.view.screen.setting

import jp.kaleidot725.adbpad.view.common.ChildStateHolder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SettingCommandStateHolder() : ChildStateHolder<SettingCommandState> {
    override val state: StateFlow<SettingCommandState> = MutableStateFlow(SettingCommandState())

    override fun setup() {
    }

    override fun dispose() {
    }
}
