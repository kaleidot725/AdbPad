package jp.kaleidot725.adbpad

import jp.kaleidot725.adbpad.model.data.Dialog
import java.io.File

data class MainState(
    // Dialog
    val dialog: Dialog? = null,


    // Screenshot
    val imageFile1: File? = null,
    val imageFile2: File? = null
) {
}
