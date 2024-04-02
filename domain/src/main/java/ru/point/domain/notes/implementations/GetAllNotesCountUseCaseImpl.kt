package ru.point.domain.notes.implementations

import kotlinx.coroutines.flow.Flow
import ru.point.data.data.repository_interfaces.NotesRepository

class GetAllNotesCountUseCaseImpl(
    private val repository: NotesRepository
) : ru.point.domain.notes.interfaces.GetAllNotesCountUseCase {
    override fun invoke(): Flow<Int> {
        return repository.getNoteCount()
    }
}