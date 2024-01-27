package com.example.appcash.domain.notes.implementations

import android.util.Log
import com.example.appcash.data.entities.Note
import com.example.appcash.data.repository_interfaces.NoteToFolderLinkRepository
import com.example.appcash.domain.notes.interfaces.GetNotesByFolderIdUseCase
import com.example.appcash.utils.events.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class GetNotesByFolderIdUseCaseImpl @Inject constructor(
    private val repository: NoteToFolderLinkRepository
) : GetNotesByFolderIdUseCase {
    override fun invoke(id: Long, onError: (Event.ErrorEvent) -> Unit): Flow<List<Note>> {
        return try {
            repository.getNotesByFolderId(id = id)
        } catch (ex: Exception) {
            onError(Event.ErrorEvent)

            Log.e("Selection exception", ex.stackTrace.contentDeepToString())
            flowOf()
        }
    }
}