package jp.kaleidot725.adbpad.domain.usecase.event

import jp.kaleidot725.adbpad.domain.model.log.Event
import jp.kaleidot725.adbpad.domain.repository.EventRepository
import kotlinx.coroutines.flow.SharedFlow

class GetEventFlowUseCase(
    private val eventRepository: EventRepository
) {
    operator fun invoke(): SharedFlow<Event> {
        return eventRepository.event
    }
}
