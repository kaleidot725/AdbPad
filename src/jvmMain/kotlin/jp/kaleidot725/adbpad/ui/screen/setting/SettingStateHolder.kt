package jp.kaleidot725.adbpad.ui.screen.setting

import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.domain.model.setting.Appearance
import jp.kaleidot725.adbpad.domain.usecase.adb.RestartAdbUseCase
import jp.kaleidot725.adbpad.domain.usecase.appearance.GetAppearanceUseCase
import jp.kaleidot725.adbpad.domain.usecase.appearance.SaveAppearanceUseCase
import jp.kaleidot725.adbpad.domain.usecase.language.GetLanguageUseCase
import jp.kaleidot725.adbpad.domain.usecase.language.SaveLanguageUseCase
import jp.kaleidot725.adbpad.domain.usecase.sdkpath.GetSdkPathUseCase
import jp.kaleidot725.adbpad.domain.usecase.sdkpath.SaveSdkPathUseCase
import jp.kaleidot725.adbpad.ui.common.ChildStateHolder
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
    private val saveAppearanceUseCase: SaveAppearanceUseCase,
    private val getLanguageUseCase: GetLanguageUseCase,
    private val saveLanguageUseCase: SaveLanguageUseCase,
    private val restartAdbUseCase: RestartAdbUseCase,
) : ChildStateHolder<SettingState> {
    private val coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main + Dispatchers.IO)
    private val language: MutableStateFlow<Language.Type> = MutableStateFlow(Language.Type.ENGLISH)
    private val appearance: MutableStateFlow<Appearance> = MutableStateFlow(Appearance.DARK)
    private val adbDirectoryPath: MutableStateFlow<String> = MutableStateFlow("")
    private val adbPortNumber: MutableStateFlow<String> = MutableStateFlow("")
    private val isRestartingAdb: MutableStateFlow<Boolean> = MutableStateFlow(false)
    override val state: StateFlow<SettingState> =
        combine(
            language,
            appearance,
            adbDirectoryPath,
            adbPortNumber,
            isRestartingAdb,
        ) { language, appearance, adbDirectoryPath, adbPortNumber, isRestartingAdb ->
            SettingState(Language.Type.entries, language, appearance, adbDirectoryPath, adbPortNumber, isRestartingAdb)
        }.stateIn(coroutineScope, SharingStarted.WhileSubscribed(), SettingState())

    private var oldAdbDirectoryPath: String = ""
    private var oldAdbPortNumber: Int = 0

    override fun setup() {
        loadSetting()
    }

    override fun refresh() {}

    override fun dispose() {
        coroutineScope.cancel()
    }

    fun save(onSaved: () -> Unit) {
        saveSetting(onSaved)
    }

    fun updateLanguage(value: Language.Type) {
        language.value = value
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

    private fun saveSetting(onSaved: () -> Unit) {
        coroutineScope.launch {
            saveLanguageUseCase(language.value)
            saveAppearanceUseCase(appearance = appearance.value)
            saveSdkPathUseCase(adbDirectoryPath.value, adbPortNumber.value.toIntOrNull())
            restartAdbUseCase(oldAdbDirectory = oldAdbDirectoryPath, oldServerPort = oldAdbPortNumber)
            onSaved()
        }
    }

    private fun loadSetting() {
        coroutineScope.launch {
            language.value = getLanguageUseCase()
            appearance.value = getAppearanceUseCase()
            val sdkPath = getSdkPathUseCase()
            adbDirectoryPath.value = sdkPath.adbDirectory
            oldAdbDirectoryPath = sdkPath.adbDirectory

            adbPortNumber.value = sdkPath.adbServerPort.toString()
            oldAdbPortNumber = sdkPath.adbServerPort
        }
    }
}
