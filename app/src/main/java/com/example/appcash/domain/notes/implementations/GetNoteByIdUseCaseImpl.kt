package com.example.appcash.domain.notes.implementations

import android.util.Log
import com.example.appcash.data.entities.Note
import com.example.appcash.data.repository_interfaces.NotesRepository
import com.example.appcash.domain.notes.interfaces.GetNoteByIdUseCase
import com.example.appcash.utils.events.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

class GetNoteByIdUseCaseImpl @Inject constructor(
    private val repository: NotesRepository
): GetNoteByIdUseCase {
    override fun invoke(id: Long, onError: (Event.ErrorEvent) -> Unit): Flow<Note> {
        return try {
            repository.getNoteById(id = id)
        } catch (ex: Exception) {
            onError(Event.ErrorEvent)

            Log.e("Selection note exception", ex.stackTrace.contentToString())
            emptyFlow()
        }
    }
}