package jp.kaleidot725.adbpad.ui.screen.newdisplay.state

import jp.kaleidot725.adbpad.core.mvi.MVIAction
import jp.kaleidot725.adbpad.core.mvi.MVISideEffect
import jp.kaleidot725.adbpad.core.mvi.MVIState
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.model.scrcpy.ScrcpyNewDisplayProfile
import jp.kaleidot725.adbpad.domain.model.sort.SortType
import java.util.Locale

sealed class ScrcpyNewDisplayAction : MVIAction {
    data class SelectProfile(val profileId: String) : ScrcpyNewDisplayAction()
    data object LaunchSelectedProfile : ScrcpyNewDisplayAction()
    data class UpdateSearchText(val text: String) : ScrcpyNewDisplayAction()
    data class UpdateSortType(val sortType: SortType) : ScrcpyNewDisplayAction()
}

enum class ScrcpyNewDisplayFailureReason {
    NoDevice,
    ProcessError,
}

sealed class ScrcpyNewDisplayFeedback {
    data object None : ScrcpyNewDisplayFeedback()
    data object Success : ScrcpyNewDisplayFeedback()
    data class Failure(val reason: ScrcpyNewDisplayFailureReason) : ScrcpyNewDisplayFeedback()
}

data class ScrcpyNewDisplayState(
    val profiles: List<ScrcpyNewDisplayProfile> = emptyList(),
    val selectedProfileId: String? = null,
    val selectedDevice: Device? = null,
    val searchText: String = "",
    val sortType: SortType = SortType.SORT_BY_NAME_ASC,
    val isLaunching: Boolean = false,
    val feedback: ScrcpyNewDisplayFeedback = ScrcpyNewDisplayFeedback.None,
) : MVIState {
    val filteredProfiles: List<ScrcpyNewDisplayProfile>
        get() = filterScrcpyNewDisplayProfiles(profiles, searchText, sortType)

    val selectedProfile: ScrcpyNewDisplayProfile?
        get() = filteredProfiles.firstOrNull { it.id == selectedProfileId } ?: filteredProfiles.firstOrNull()

    val canLaunch: Boolean
        get() = selectedDevice != null && selectedProfile != null && !isLaunching
}

class ScrcpyNewDisplaySideEffect : MVISideEffect

internal fun filterScrcpyNewDisplayProfiles(
    profiles: List<ScrcpyNewDisplayProfile>,
    query: String,
    sortType: SortType,
): List<ScrcpyNewDisplayProfile> {
    val normalized = query.trim().lowercase(Locale.getDefault())
    if (normalized.isBlank()) {
        return profiles.sortedWith(sortType)
    }

    val filtered = profiles.filter { profile ->
        val candidates =
            buildList {
                add(profile.displayName)
                add(profile.shortSpec)
                profile.note?.let { add(it) }
                add(profile.brand.name)
                add(profile.formFactor.name)
            }

        candidates.any { candidate -> candidate.contains(normalized, ignoreCase = true) }
    }

    return filtered.sortedWith(sortType)
}

private fun List<ScrcpyNewDisplayProfile>.sortedWith(sortType: SortType): List<ScrcpyNewDisplayProfile> =
    when (sortType) {
        SortType.SORT_BY_NAME_ASC -> this.sortedBy { it.displayName.lowercase(Locale.getDefault()) }
        SortType.SORT_BY_NAME_DESC -> this.sortedByDescending { it.displayName.lowercase(Locale.getDefault()) }
    }
