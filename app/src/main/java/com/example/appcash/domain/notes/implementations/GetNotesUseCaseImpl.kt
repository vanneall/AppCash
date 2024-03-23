package com.example.appcash.domain.notes.implementations

import android.util.Log
import com.example.appcash.data.entities.Note
import com.example.appcash.data.repository_interfaces.NotesRepository
import com.example.appcash.domain.notes.interfaces.GetNotesUseCase
import com.example.appcash.utils.events.Event.ErrorEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class GetNotesUseCaseImpl @Inject constructor(
    private val repository: NotesRepository
) : GetNotesUseCase {
    override fun invoke(folderId: Long?, onError: (ErrorEvent) -> Unit): Flow<List<Note>> {
        return try {
            if (folderId != null) {
                repository.getNotes(folderId)
            } else {
                repository.getNotes()
            }

        } catch (ex: Exception) {
            onError(ErrorEvent)

            Log.e("Selection exception", ex.stackTrace.contentDeepToString())
            flowOf()
        }
    }
}