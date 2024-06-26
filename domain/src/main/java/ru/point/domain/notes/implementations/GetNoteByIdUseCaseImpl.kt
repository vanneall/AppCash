package ru.point.domain.notes.implementations

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.point.data.data.entity.entities.Note
import ru.point.data.data.datasource.repository.interfaces.NotesRepository
import ru.point.domain.notes.interfaces.GetNoteByIdUseCase
import javax.inject.Inject

class GetNoteByIdUseCaseImpl @Inject constructor(
    private val repository: NotesRepository
) : GetNoteByIdUseCase {
    override fun invoke(id: Long): Flow<Note> {
        return flow {
            repository.getNoteById(id = id).collect { note ->
                emit(note)
            }
        }
    }
}