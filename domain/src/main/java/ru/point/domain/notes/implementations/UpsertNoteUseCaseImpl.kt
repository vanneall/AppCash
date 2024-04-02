package ru.point.domain.notes.implementations

import ru.point.data.data.entities.Note
import ru.point.data.data.repository_interfaces.NotesRepository
import ru.point.domain.notes.interfaces.UpsertNoteUseCase
import javax.inject.Inject

class UpsertNoteUseCaseImpl @Inject constructor(
    private val repository: NotesRepository,
) : UpsertNoteUseCase {
    override fun invoke(
        title: String,
        content: String,
        folderId: Long?
    ) {
        repository.createNote(
            Note(
                title = title,
                content = content,
                folderId = folderId
            )
        )
    }

    override fun invoke(
        id: Long,
        title: String,
        content: String,
        folderId: Long?
    ) {
        repository.updateNote(
            Note(
                id = id,
                title = title,
                content = content,
                folderId = folderId
            )
        )
    }
}