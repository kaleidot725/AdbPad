package jp.kaleidot725.adbpad.domain.model.scrcpy

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

enum class ScrcpyBrand {
    PIXEL,
    GALAXY,
}

enum class ScrcpyFormFactor {
    BAR,
    FOLDABLE,
}

/**
 * Predefined profile describing a virtual display created via `scrcpy --new-display`.
 */
data class ScrcpyNewDisplayProfile(
    val id: String,
    val displayName: String,
    val brand: ScrcpyBrand,
    val formFactor: ScrcpyFormFactor,
    val width: Int,
    val height: Int,
    val densityDpi: Int?,
    val refreshRateHz: Int?,
    val note: String? = null,
) {
    val aspectRatio: String = calculateAspectRatio(width, height)
    val shortSpec: String = "${max(width, height)} x ${min(width, height)} (${aspectRatio})"

    companion object {
        private fun calculateAspectRatio(width: Int, height: Int): String {
            val gcdValue = gcd(width, height)
            val w = width / gcdValue
            val h = height / gcdValue
            return "${max(w, h)}:${min(w, h)}"
        }

        private tailrec fun gcd(a: Int, b: Int): Int {
            val absA = abs(a)
            val absB = abs(b)
            return if (absB == 0) absA else gcd(absB, absA % absB)
        }
    }
}

fun defaultScrcpyNewDisplayProfiles(): List<ScrcpyNewDisplayProfile> =
    listOf(
        ScrcpyNewDisplayProfile(
            id = "pixel-9-pro",
            displayName = "Pixel 9 Pro",
            brand = ScrcpyBrand.PIXEL,
            formFactor = ScrcpyFormFactor.BAR,
            width = 1344,
            height = 2992,
            densityDpi = 489,
            refreshRateHz = 120,
            note = "6.3\" LTPO OLED"
        ),
        ScrcpyNewDisplayProfile(
            id = "pixel-9",
            displayName = "Pixel 9",
            brand = ScrcpyBrand.PIXEL,
            formFactor = ScrcpyFormFactor.BAR,
            width = 1080,
            height = 2424,
            densityDpi = 425,
            refreshRateHz = 120,
            note = "6.24\" Actua OLED"
        ),
        ScrcpyNewDisplayProfile(
            id = "pixel-fold-2",
            displayName = "Pixel Fold 2",
            brand = ScrcpyBrand.PIXEL,
            formFactor = ScrcpyFormFactor.FOLDABLE,
            width = 2076,
            height = 2152,
            densityDpi = 370,
            refreshRateHz = 120,
            note = "7.9\" inner display"
        ),
        ScrcpyNewDisplayProfile(
            id = "galaxy-s24-ultra",
            displayName = "Galaxy S24 Ultra",
            brand = ScrcpyBrand.GALAXY,
            formFactor = ScrcpyFormFactor.BAR,
            width = 1440,
            height = 3120,
            densityDpi = 505,
            refreshRateHz = 120,
            note = "6.8\" Dynamic AMOLED 2X"
        ),
        ScrcpyNewDisplayProfile(
            id = "galaxy-s24",
            displayName = "Galaxy S24",
            brand = ScrcpyBrand.GALAXY,
            formFactor = ScrcpyFormFactor.BAR,
            width = 1080,
            height = 2340,
            densityDpi = 425,
            refreshRateHz = 120,
            note = "6.2\" Dynamic AMOLED 2X"
        ),
        ScrcpyNewDisplayProfile(
            id = "galaxy-z-fold6",
            displayName = "Galaxy Z Fold6",
            brand = ScrcpyBrand.GALAXY,
            formFactor = ScrcpyFormFactor.FOLDABLE,
            width = 2160,
            height = 1856,
            densityDpi = 374,
            refreshRateHz = 120,
            note = "7.6\" inner display"
        ),
    )
