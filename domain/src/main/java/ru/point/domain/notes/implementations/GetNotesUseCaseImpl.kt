package ru.point.domain.notes.implementations

import kotlinx.coroutines.flow.Flow
import ru.point.data.data.entities.Note
import ru.point.data.data.repository_interfaces.NotesRepository
import ru.point.domain.notes.interfaces.GetNotesUseCase
import javax.inject.Inject

class GetNotesUseCaseImpl @Inject constructor(
    private val repository: NotesRepository
) : GetNotesUseCase {
    override fun invoke(folderId: Long?): Flow<List<Note>> {
        return if (folderId != null) {
            repository.getNotes(folderId)
        } else {
            repository.getNotes()
        }
    }
}