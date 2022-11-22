package jp.kaleidot725.adbpad.domain.usecase.event

import jp.kaleidot725.adbpad.domain.repository.EventRepository
import jp.kaleidot725.adbpad.view.model.Event
import kotlinx.coroutines.flow.SharedFlow

class GetEventFlowUseCase(
    private val eventRepository: EventRepository
) {
    operator fun invoke(): SharedFlow<Event> {
        return eventRepository.event
    }
}
