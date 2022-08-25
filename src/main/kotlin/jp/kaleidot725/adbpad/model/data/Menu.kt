package jp.kaleidot725.adbpad.model.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DoubleArrow
import androidx.compose.material.icons.filled.Input
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Menu(val title: String, val icon: ImageVector) {
    object Command : Menu("コマンド", Icons.Default.DoubleArrow)
    object InputText : Menu("テキスト入力", Icons.Default.Input)

    object Screenshot : Menu("スクリーンショット", Icons.Default.PhotoCamera)
}
