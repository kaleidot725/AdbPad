package jp.kaleidot725.adbpad.domain.model

class Event(val message: String) {
    companion object {
        val NULL_EVENT = Event("")
    }
}
