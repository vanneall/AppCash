package ru.point.domain.notes.implementations

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.point.data.data.datasource.repository.interfaces.NotesRepository

class GetAllNotesCountUseCaseImpl(
    private val repository: NotesRepository
) : ru.point.domain.notes.interfaces.GetAllNotesCountUseCase {
    override fun invoke(): Flow<Int> {
        return flow {
            repository.getNoteCount().collect { count ->
                emit(count)
            }
        }
    }
}