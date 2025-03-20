package jp.kaleidot725.adbpad.ui.common.resource

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object UserColor {
    @Composable
    fun getSplitterColor(): Color {
        val isLight = MaterialTheme.colors.isLight
        return if (isLight) {
            Color(0xFF333333).copy(alpha = 0.15f)
        } else {
            Color(0x0FFDDDDDD).copy(alpha = 0.15f)
        }
    }

    @Composable
    fun getFloatingBackgroundColor(): Color {
        val isLight = MaterialTheme.colors.isLight
        return if (isLight) {
            Color(0xFFDDDDDD)
        } else {
            Color(0x0FF333333)
        }
    }

    object Light {
        val PRIMARY = Color(0xFF006B5D)
        val PRIMARY_VARIANT = Color(0xFFFFFFFF)
        val ON_PRIMARY = Color(0xFFFFFFFF)
        val SECONDARY = Color(0xFF46645E)
        val SECONDARY_VARIANT = Color(0xFF46645E)
        val ON_SECONDARY = Color(0xFFFFFFFF)
        val ERROR = Color(0xFFBA1A1A)
        val ON_ERROR = Color(0xFFFFFFFF)
        val BACKGROUND = Color(0xFFF8F8F8)
        val ON_BACKGROUND = Color(0xFF191C1B)
        val SURFACE = Color(0xFFFAFDFA)
        val ON_SURFACE = Color(0xFF191C1B)
    }

    object Dark {
        val PRIMARY = Color(0xFF90ccff)
        val PRIMARY_VARIANT = Color(0xFF90ccff)
        val ON_PRIMARY = Color(0xFF003352)
        val SECONDARY = Color(0xFFb8c8da)
        val SECONDARY_VARIANT = Color(0xFFb8c8da)
        val ON_SECONDARY = Color(0xFF233240)
        val ERROR = Color(0xFFffb4a9)
        val ON_ERROR = Color(0xFF680003)
        val BACKGROUND = Color(0xFF1a1c1e)
        val ON_BACKGROUND = Color(0xFFe2e2e5)
        val SURFACE = Color(0xFF1a1c1e)
        val ON_SURFACE = Color(0xFFe2e2e5)
    }
}
