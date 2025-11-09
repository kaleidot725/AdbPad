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
    TABLET,
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
    val shortSpec: String = "${max(width, height)} x ${min(width, height)} ($aspectRatio)"

    companion object {
        private fun calculateAspectRatio(
            width: Int,
            height: Int,
        ): String {
            val gcdValue = gcd(width, height)
            val w = width / gcdValue
            val h = height / gcdValue
            return "${max(w, h)}:${min(w, h)}"
        }

        private tailrec fun gcd(
            a: Int,
            b: Int,
        ): Int {
            val absA = abs(a)
            val absB = abs(b)
            return if (absB == 0) absA else gcd(absB, absA % absB)
        }
    }
}

fun defaultScrcpyNewDisplayProfiles(): List<ScrcpyNewDisplayProfile> =
    listOf(
        ScrcpyNewDisplayProfile(
            id = "pixel-10",
            displayName = "Pixel 10",
            brand = ScrcpyBrand.PIXEL,
            formFactor = ScrcpyFormFactor.BAR,
            width = 1080,
            height = 2424,
            densityDpi = 422,
            refreshRateHz = 120,
            note = "6.3\" Actua OLED (60-120Hz)",
        ),
        ScrcpyNewDisplayProfile(
            id = "pixel-10-pro",
            displayName = "Pixel 10 Pro",
            brand = ScrcpyBrand.PIXEL,
            formFactor = ScrcpyFormFactor.BAR,
            width = 1280,
            height = 2856,
            densityDpi = 495,
            refreshRateHz = 120,
            note = "6.3\" Super Actua LTPO OLED (1-120Hz)",
        ),
        ScrcpyNewDisplayProfile(
            id = "pixel-10-pro-xl",
            displayName = "Pixel 10 Pro XL",
            brand = ScrcpyBrand.PIXEL,
            formFactor = ScrcpyFormFactor.BAR,
            width = 1344,
            height = 2992,
            densityDpi = 486,
            refreshRateHz = 120,
            note = "6.8\" Super Actua LTPO OLED (1-120Hz)",
        ),
        ScrcpyNewDisplayProfile(
            id = "pixel-tablet-2023",
            displayName = "Pixel Tablet",
            brand = ScrcpyBrand.PIXEL,
            formFactor = ScrcpyFormFactor.TABLET,
            width = 1600,
            height = 2560,
            densityDpi = 276,
            refreshRateHz = 60,
            note = "10.95\" LCD (16:10, USI 2.0)",
        ),
        ScrcpyNewDisplayProfile(
            id = "galaxy-s25",
            displayName = "Galaxy S25",
            brand = ScrcpyBrand.GALAXY,
            formFactor = ScrcpyFormFactor.BAR,
            width = 1080,
            height = 2340,
            densityDpi = 416,
            refreshRateHz = 120,
            note = "6.2\" Dynamic AMOLED 2X (1-120Hz)",
        ),
        ScrcpyNewDisplayProfile(
            id = "galaxy-s25-ultra",
            displayName = "Galaxy S25 Ultra",
            brand = ScrcpyBrand.GALAXY,
            formFactor = ScrcpyFormFactor.BAR,
            width = 1440,
            height = 3120,
            densityDpi = 505,
            refreshRateHz = 120,
            note = "6.9\" Dynamic AMOLED 2X (1-120Hz)",
        ),
        ScrcpyNewDisplayProfile(
            id = "galaxy-z-fold7-main",
            displayName = "Galaxy Z Fold7 (Main)",
            brand = ScrcpyBrand.GALAXY,
            formFactor = ScrcpyFormFactor.FOLDABLE,
            width = 1968,
            height = 2184,
            densityDpi = 368,
            refreshRateHz = 120,
            note = "8.0\" Dynamic AMOLED 2X inner display",
        ),
        ScrcpyNewDisplayProfile(
            id = "galaxy-z-fold7-cover",
            displayName = "Galaxy Z Fold7 (Cover)",
            brand = ScrcpyBrand.GALAXY,
            formFactor = ScrcpyFormFactor.FOLDABLE,
            width = 1080,
            height = 2520,
            densityDpi = 422,
            refreshRateHz = 120,
            note = "6.5\" Dynamic AMOLED 2X cover display",
        ),
        ScrcpyNewDisplayProfile(
            id = "galaxy-z-flip7-main",
            displayName = "Galaxy Z Flip7 (Main)",
            brand = ScrcpyBrand.GALAXY,
            formFactor = ScrcpyFormFactor.FOLDABLE,
            width = 1080,
            height = 2520,
            densityDpi = 397,
            refreshRateHz = 120,
            note = "6.9\" Dynamic AMOLED 2X inner display",
        ),
        ScrcpyNewDisplayProfile(
            id = "galaxy-z-flip7-cover",
            displayName = "Galaxy Z Flip7 (Cover)",
            brand = ScrcpyBrand.GALAXY,
            formFactor = ScrcpyFormFactor.FOLDABLE,
            width = 948,
            height = 1048,
            densityDpi = null,
            refreshRateHz = 120,
            note = "4.1\" Super AMOLED cover display",
        ),
        ScrcpyNewDisplayProfile(
            id = "galaxy-tab-s11",
            displayName = "Galaxy Tab S11",
            brand = ScrcpyBrand.GALAXY,
            formFactor = ScrcpyFormFactor.TABLET,
            width = 1600,
            height = 2560,
            densityDpi = 274,
            refreshRateHz = 120,
            note = "11.0\" Dynamic AMOLED 2X",
        ),
        ScrcpyNewDisplayProfile(
            id = "galaxy-tab-s11-ultra",
            displayName = "Galaxy Tab S11 Ultra",
            brand = ScrcpyBrand.GALAXY,
            formFactor = ScrcpyFormFactor.TABLET,
            width = 1848,
            height = 2960,
            densityDpi = 239,
            refreshRateHz = 120,
            note = "14.6\" Dynamic AMOLED 2X",
        ),
    )
