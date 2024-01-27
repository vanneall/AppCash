package com.example.appcash.domain.notes.implementations

import android.util.Log
import com.example.appcash.data.entities.Note
import com.example.appcash.data.repository_interfaces.NoteRepository
import com.example.appcash.domain.notes.interfaces.UpsertNoteUseCase
import com.example.appcash.utils.events.Event
import com.example.appcash.view.notes.note_info.components.NoteOpenMode
import javax.inject.Inject

class UpsertNoteUseCaseImpl @Inject constructor(
    private val repository: NoteRepository,
) : UpsertNoteUseCase {
    override fun invoke(
        noteId: Long?,
        title: String,
        content: String,
        openMode: NoteOpenMode,
        onError: (Event.ErrorEvent) -> Unit,
        folderId: Long?,
    ) {
        try {
            when {
                openMode == NoteOpenMode.CREATE -> {
                    if (folderId != null) {
                        insertWithFolderLink(
                            folderId = folderId,
                            title = title,
                            content = content)
                    } else {
                        insertWithoutFolderLink(
                            title = title,
                            content = content
                        )
                    }
                }

                openMode == NoteOpenMode.EDIT && noteId != null -> {
                    updateNote(
                        id = noteId,
                        title = title,
                        content = content
                    )
                }

                else -> throw Exception("Something went wrong")
            }

        } catch (ex: Exception) {
            onError(Event.ErrorEvent)

            Log.e("Upsert note exception", ex.stackTrace.contentToString())
        }
    }

    private fun insertWithFolderLink(folderId: Long, title: String, content: String) {
        repository.insertLinkedNote(
            folderId = folderId,
            note = Note(
                title = title,
                content = content
            )
        )
    }

    private fun insertWithoutFolderLink(title: String, content: String) {
        repository.insertNote(
            note = Note(
                title = title,
                content = content
            )
        )
    }

    private fun updateNote(id: Long, title: String, content: String) {
        repository.updateNote(
            note = Note(
                id = id,
                title = title,
                content = content
            )
        )
    }
}