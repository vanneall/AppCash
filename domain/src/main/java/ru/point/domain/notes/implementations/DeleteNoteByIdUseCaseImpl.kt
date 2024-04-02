package ru.point.domain.notes.implementations

import ru.point.data.data.repository_interfaces.NotesRepository
import ru.point.domain.notes.interfaces.DeleteNoteByIdUseCase
import javax.inject.Inject

class DeleteNoteByIdUseCaseImpl @Inject constructor(
    private val repository: NotesRepository
) : DeleteNoteByIdUseCase {
    override fun invoke(id: Long) {
        repository.deleteNote(id = id)
    }
}