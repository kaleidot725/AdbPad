package jp.kaleidot725.adbpad.domain.repository

import jp.kaleidot725.adbpad.view.model.Event
import kotlinx.coroutines.flow.SharedFlow

interface EventRepository {
    val event: SharedFlow<Event>
    suspend fun sendEvent(event: Event)
}
