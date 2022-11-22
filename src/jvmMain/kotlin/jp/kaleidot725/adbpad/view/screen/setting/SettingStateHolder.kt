package jp.kaleidot725.adbpad.view.screen.setting

import jp.kaleidot725.adbpad.view.common.ChildStateHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class SettingStateHolder() : ChildStateHolder<SettingState> {
    private val coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main + Dispatchers.IO)
    private val adbDirectoryPath: MutableStateFlow<String> = MutableStateFlow("")
    private val adbPortNumber: MutableStateFlow<String> = MutableStateFlow("")
    private val sdkAndroidDirectoryPath: MutableStateFlow<String> = MutableStateFlow("")
    override val state: StateFlow<SettingState> = combine(
        adbDirectoryPath,
        adbPortNumber,
        sdkAndroidDirectoryPath
    ) { adbDirectoryPath, adbPortNumber, sdkAndroidDirectoryPath ->
        SettingState(adbDirectoryPath, adbPortNumber, sdkAndroidDirectoryPath)
    }.stateIn(coroutineScope, SharingStarted.WhileSubscribed(), SettingState())

    override fun setup() {

    }

    fun updateAdbDirectoryPath(value: String) {
        adbDirectoryPath.value = value
    }

    fun updateAdbPortNumberPath(value: String) {
        adbPortNumber.value = value
    }

    fun updateSdkAndroidDirectoryPath(value: String) {
        sdkAndroidDirectoryPath.value = value
    }

    override fun dispose() {
    }
}
