package jp.kaleidot725.adbpad.view.screen.setting

import jp.kaleidot725.adbpad.domain.model.setting.Appearance
import jp.kaleidot725.adbpad.domain.usecase.appearance.GetAppearanceUseCase
import jp.kaleidot725.adbpad.domain.usecase.appearance.SaveAppearanceUseCase
import jp.kaleidot725.adbpad.domain.usecase.sdkpath.GetSdkPathUseCase
import jp.kaleidot725.adbpad.domain.usecase.sdkpath.SaveSdkPathUseCase
import jp.kaleidot725.adbpad.view.common.ChildStateHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingStateHolder(
    private val getSdkPathUseCase: GetSdkPathUseCase,
    private val saveSdkPathUseCase: SaveSdkPathUseCase,
    private val getAppearanceUseCase: GetAppearanceUseCase,
    private val saveAppearanceUseCase: SaveAppearanceUseCase
) : ChildStateHolder<SettingState> {
    private val coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main + Dispatchers.IO)
    private val appearance: MutableStateFlow<Appearance> = MutableStateFlow(Appearance.DARK)
    private val adbDirectoryPath: MutableStateFlow<String> = MutableStateFlow("")
    private val adbPortNumber: MutableStateFlow<String> = MutableStateFlow("")
    private val androidSdkDirectoryPath: MutableStateFlow<String> = MutableStateFlow("")
    override val state: StateFlow<SettingState> = combine(
        appearance,
        adbDirectoryPath,
        adbPortNumber,
        androidSdkDirectoryPath
    ) { appearance, adbDirectoryPath, adbPortNumber, androidSdkDirectoryPath ->
        SettingState(appearance, adbDirectoryPath, adbPortNumber, androidSdkDirectoryPath)
    }.stateIn(coroutineScope, SharingStarted.WhileSubscribed(), SettingState())

    override fun setup() {
        loadPath()
    }

    override fun dispose() {
        coroutineScope.cancel()
    }

    fun save() {
        savePath()
    }

    fun cancel() {
        loadPath()
    }

    fun updateAppearance(value: Appearance) {
        appearance.value = value
    }

    fun updateAdbDirectoryPath(value: String) {
        adbDirectoryPath.value = value
    }

    fun updateAdbPortNumberPath(value: String) {
        adbPortNumber.value = value
    }

    fun updateAndroidSdkDirectoryPath(value: String) {
        androidSdkDirectoryPath.value = value
    }

    private fun savePath() {
        coroutineScope.launch {
            saveSdkPathUseCase(
                adbDirectoryPath.value,
                adbPortNumber.value.toIntOrNull(),
                androidSdkDirectoryPath.value
            )
            saveAppearanceUseCase(
                appearance = appearance.value
            )
        }
    }

    private fun loadPath() {
        coroutineScope.launch {
            val sdkPath = getSdkPathUseCase()
            adbDirectoryPath.value = sdkPath.adbDirectory
            adbPortNumber.value = sdkPath.adbServerPort.toString()
            androidSdkDirectoryPath.value = sdkPath.androidSdkDirectory
            appearance.value = getAppearanceUseCase()
        }
    }
}