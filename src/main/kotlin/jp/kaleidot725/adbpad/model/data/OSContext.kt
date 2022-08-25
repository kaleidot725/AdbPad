package jp.kaleidot725.adbpad.model.data

sealed class OSContext(val direcotry: String = "") {
    object Mac : OSContext(System.getProperty("user.home") + "/Library/Application Support/adbpad/")
    object Windows : OSContext(System.getProperty("user.home") + "/adbpad/")
    companion object {
        fun resolveOSContext(): OSContext {
            return when (System.getProperty("os.name")) {
                "Mac OS X" -> Mac
                else -> Windows
            }
        }
    }
}
