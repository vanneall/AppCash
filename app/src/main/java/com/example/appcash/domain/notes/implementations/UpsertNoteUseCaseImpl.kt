package com.example.appcash.domain.notes.implementations

import com.example.appcash.data.entities.Note
import com.example.appcash.data.repository_interfaces.NotesRepository
import com.example.appcash.domain.notes.interfaces.UpsertNoteUseCase
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