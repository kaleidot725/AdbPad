package jp.kaleidot725.adbpad.view.model

import androidx.compose.ui.window.FrameWindowScope
import kotlinx.serialization.Serializable

@Serializable
data class WindowSize(
    val width: Int,
    val height: Int
) {
    companion object {
        val UNKNOWN = WindowSize(-1, -1)
        val DEFAULT = WindowSize(800, 400)
    }
}

fun FrameWindowScope.getWindowSize(): WindowSize {
    return WindowSize(
        this.window.width,
        this.window.height
    )
}
