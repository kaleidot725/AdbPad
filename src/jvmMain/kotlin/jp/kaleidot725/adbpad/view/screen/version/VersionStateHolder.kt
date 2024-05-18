package jp.kaleidot725.adbpad.view.screen.version

import jp.kaleidot725.adbpad.domain.model.version.Version
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
import ui.repository.VersionRepository

class VersionStateHolder(
    private val versionRepository: VersionRepository,
) : ChildStateHolder<VersionState> {
    private val coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main + Dispatchers.IO)
    private val isLoading: MutableStateFlow<Boolean> = MutableStateFlow(true)
    private val hasError: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val versions: MutableStateFlow<List<Version>> = MutableStateFlow(emptyList())

    override val state: StateFlow<VersionState> =
        combine(versions, isLoading, hasError) { versions, isLoading, hasError ->
            VersionState(versions, isLoading, hasError)
        }.stateIn(coroutineScope, SharingStarted.WhileSubscribed(), VersionState(emptyList()))

    override fun setup() {
        retry()
    }

    fun retry() {
        coroutineScope.launch {
            isLoading.value = true
            val result = versionRepository.getVersions()
            if (result != null) {
                versions.value = result
                isLoading.value = false
            } else {
                versions.value = emptyList()
                hasError.value = true
                isLoading.value = false
            }
        }
    }

    override fun dispose() {
        coroutineScope.cancel()
    }
}
