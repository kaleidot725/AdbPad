package jp.kaleidot725.adbpad.view.screen.setting

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
    private val saveSdkPathUseCase: SaveSdkPathUseCase
) : ChildStateHolder<SettingState> {
    private val coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main + Dispatchers.IO)
    private val adbDirectoryPath: MutableStateFlow<String> = MutableStateFlow("")
    private val adbPortNumber: MutableStateFlow<String> = MutableStateFlow("")
    private val androidSdkDirectoryPath: MutableStateFlow<String> = MutableStateFlow("")
    override val state: StateFlow<SettingState> = combine(
        adbDirectoryPath,
        adbPortNumber,
        androidSdkDirectoryPath
    ) { adbDirectoryPath, adbPortNumber, androidSdkDirectoryPath ->
        SettingState(adbDirectoryPath, adbPortNumber, androidSdkDirectoryPath)
    }.stateIn(coroutineScope, SharingStarted.WhileSubscribed(), SettingState())

    override fun setup() = loadPath()

    override fun dispose() = coroutineScope.cancel()

    fun save() = savePath()

    fun cancel() = loadPath()

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
        }
    }

    private fun loadPath() {
        coroutineScope.launch {
            val sdkPath = getSdkPathUseCase()
            adbDirectoryPath.value = sdkPath.adbDirectory
            adbPortNumber.value = sdkPath.adbServerPort.toString()
            androidSdkDirectoryPath.value = sdkPath.androidSdkDirectory
        }
    }
}
