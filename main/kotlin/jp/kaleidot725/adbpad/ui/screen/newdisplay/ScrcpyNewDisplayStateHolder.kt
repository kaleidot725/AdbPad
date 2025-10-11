package jp.kaleidot725.adbpad.ui.screen.newdisplay

import jp.kaleidot725.adbpad.core.mvi.MVIBase
import jp.kaleidot725.adbpad.domain.model.scrcpy.ScrcpyNewDisplayProfile
import jp.kaleidot725.adbpad.domain.model.sort.SortType
import jp.kaleidot725.adbpad.domain.usecase.device.GetSelectedDeviceFlowUseCase
import jp.kaleidot725.adbpad.domain.usecase.scrcpy.GetScrcpyNewDisplayProfilesUseCase
import jp.kaleidot725.adbpad.domain.usecase.scrcpy.LaunchScrcpyNewDisplayUseCase
import jp.kaleidot725.adbpad.ui.screen.newdisplay.state.ScrcpyNewDisplayAction
import jp.kaleidot725.adbpad.ui.screen.newdisplay.state.ScrcpyNewDisplayFailureReason
import jp.kaleidot725.adbpad.ui.screen.newdisplay.state.ScrcpyNewDisplayFeedback
import jp.kaleidot725.adbpad.ui.screen.newdisplay.state.ScrcpyNewDisplaySideEffect
import jp.kaleidot725.adbpad.ui.screen.newdisplay.state.ScrcpyNewDisplayState
import jp.kaleidot725.adbpad.ui.screen.newdisplay.state.filterScrcpyNewDisplayProfiles
import kotlinx.coroutines.launch

class ScrcpyNewDisplayStateHolder(
    private val getSelectedDeviceFlowUseCase: GetSelectedDeviceFlowUseCase,
    private val getScrcpyNewDisplayProfilesUseCase: GetScrcpyNewDisplayProfilesUseCase,
    private val launchScrcpyNewDisplayUseCase: LaunchScrcpyNewDisplayUseCase,
) : MVIBase<ScrcpyNewDisplayState, ScrcpyNewDisplayAction, ScrcpyNewDisplaySideEffect>(
        initialUiState = ScrcpyNewDisplayState(),
    ) {
    override fun onSetup() {
        coroutineScope.launch {
            getSelectedDeviceFlowUseCase().collect { device ->
                update { copy(selectedDevice = device) }
            }
        }

        coroutineScope.launch {
            val profiles = getScrcpyNewDisplayProfilesUseCase()
            updateProfiles(profiles)
        }
    }

    override fun onRefresh() {
        coroutineScope.launch {
            val profiles = getScrcpyNewDisplayProfilesUseCase()
            updateProfiles(profiles)
        }
    }

    override fun onAction(uiAction: ScrcpyNewDisplayAction) {
        when (uiAction) {
            is ScrcpyNewDisplayAction.SelectProfile -> selectProfile(uiAction.profileId)
            ScrcpyNewDisplayAction.LaunchSelectedProfile -> launchSelectedProfile()
            is ScrcpyNewDisplayAction.UpdateSearchText -> updateSearchText(uiAction.text)
            is ScrcpyNewDisplayAction.UpdateSortType -> updateSortType(uiAction.sortType)
        }
    }

    private fun selectProfile(profileId: String) {
        update {
            copy(
                selectedProfileId = profileId,
                feedback = ScrcpyNewDisplayFeedback.None,
            )
        }
    }

    private fun updateSearchText(text: String) {
        update {
            val filteredProfiles = filterScrcpyNewDisplayProfiles(profiles, text, sortType)
            val nextSelection =
                when {
                    filteredProfiles.any { it.id == selectedProfileId } -> selectedProfileId
                    else -> filteredProfiles.firstOrNull()?.id
                }

            copy(
                searchText = text,
                selectedProfileId = nextSelection,
                feedback = ScrcpyNewDisplayFeedback.None,
            )
        }
    }

    private fun updateProfiles(profiles: List<ScrcpyNewDisplayProfile>) {
        update {
            val filteredProfiles = filterScrcpyNewDisplayProfiles(profiles, searchText, sortType)
            val nextSelection =
                when {
                    filteredProfiles.any { it.id == selectedProfileId } -> selectedProfileId
                    else -> filteredProfiles.firstOrNull()?.id
                }

            copy(
                profiles = profiles,
                selectedProfileId = nextSelection,
            )
        }
    }

    private fun updateSortType(sortType: SortType) {
        update {
            val filteredProfiles = filterScrcpyNewDisplayProfiles(profiles, searchText, sortType)
            val nextSelection =
                when {
                    filteredProfiles.any { it.id == selectedProfileId } -> selectedProfileId
                    else -> filteredProfiles.firstOrNull()?.id
                }

            copy(
                sortType = sortType,
                selectedProfileId = nextSelection,
                feedback = ScrcpyNewDisplayFeedback.None,
            )
        }
    }

    private fun launchSelectedProfile() {
        val profile = state.value.selectedProfile
        if (profile == null) {
            update {
                copy(
                    feedback = ScrcpyNewDisplayFeedback.Failure(ScrcpyNewDisplayFailureReason.ProcessError),
                )
            }
            return
        }

        val device = state.value.selectedDevice
        if (device == null) {
            update {
                copy(
                    feedback = ScrcpyNewDisplayFeedback.Failure(ScrcpyNewDisplayFailureReason.NoDevice),
                )
            }
            return
        }

        if (state.value.isLaunching) return

        update {
            copy(
                isLaunching = true,
                feedback = ScrcpyNewDisplayFeedback.None,
            )
        }

        coroutineScope.launch {
            val success = launchScrcpyNewDisplayUseCase(device, profile)
            update {
                copy(
                    isLaunching = false,
                    feedback =
                        if (success) {
                            ScrcpyNewDisplayFeedback.Success
                        } else {
                            ScrcpyNewDisplayFeedback.Failure(ScrcpyNewDisplayFailureReason.ProcessError)
                        },
                )
            }
        }
    }
}
