package ru.point.domain.notes.implementations

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.point.data.data.entities.Note
import ru.point.data.data.repository_interfaces.NotesRepository
import ru.point.domain.notes.interfaces.GetNotesUseCase
import javax.inject.Inject

class GetNotesUseCaseImpl @Inject constructor(
    private val repository: NotesRepository
) : GetNotesUseCase {
    override fun invoke(folderId: Long?): Flow<List<Note>> {
        return flow {
            if (folderId != null) {
                repository.getNotesByFolderId(folderId).collect { list ->
                    emit(list)
                }
            } else {
                repository.getAllNotes().collect { list ->
                    emit(list)
                }
            }
        }
    }
}