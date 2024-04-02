package ru.point.domain.notes.implementations

import kotlinx.coroutines.flow.Flow
import ru.point.data.data.entities.Note
import ru.point.data.data.repository_interfaces.NotesRepository
import ru.point.domain.notes.interfaces.GetNoteByIdUseCase
import javax.inject.Inject

class GetNoteByIdUseCaseImpl @Inject constructor(
    private val repository: NotesRepository
): GetNoteByIdUseCase {
    override fun invoke(id: Long): Flow<Note> {
        return repository.getNoteById(id = id)
    }
}