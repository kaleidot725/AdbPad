package jp.kaleidot725.adbpad.repository.impl

import jp.kaleidot725.adbpad.domain.model.Event
import jp.kaleidot725.adbpad.domain.repository.EventRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class EventRepositoryImpl : EventRepository {
    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()
    override val event: SharedFlow<Event> = _event

    override suspend fun sendEvent(event: Event) {
        _event.emit(event)
    }
}
