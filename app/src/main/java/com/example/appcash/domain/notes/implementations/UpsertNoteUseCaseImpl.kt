package com.example.appcash.domain.notes.implementations

import android.util.Log
import com.example.appcash.data.entities.Note
import com.example.appcash.data.repository_interfaces.NotesRepository
import com.example.appcash.domain.notes.interfaces.UpsertNoteUseCase
import com.example.appcash.utils.events.Event
import javax.inject.Inject

class UpsertNoteUseCaseImpl @Inject constructor(
    private val repository: NotesRepository,
) : UpsertNoteUseCase {
    override fun invoke(
        title: String,
        content: String,
        onError: (Event.ErrorEvent) -> Unit,
        folderId: Long?
    ) {
        try {
            repository.createNote(
                Note(
                    title = title,
                    content = content,
                    folderId = folderId
                )
            )
        } catch (ex: Exception) {
            onError(Event.ErrorEvent)

            Log.e("Upsert note exception", ex.stackTrace.contentToString())
        }
    }

    override fun invoke(
        id: Long,
        title: String,
        content: String,
        onError: (Event.ErrorEvent) -> Unit,
        folderId: Long?
    ) {
        try {
            repository.updateNote(
                Note(
                    id = id,
                    title = title,
                    content = content,
                    folderId = folderId
                )
            )
        } catch (ex: Exception) {
            onError(Event.ErrorEvent)

            Log.e("Upsert note exception", ex.stackTrace.contentToString())
        }
    }
}