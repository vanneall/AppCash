package com.example.appcash.domain.notes.implementations

import android.util.Log
import com.example.appcash.data.repository_interfaces.NotesRepository
import com.example.appcash.domain.notes.interfaces.DeleteNoteByIdUseCase
import com.example.appcash.utils.events.Event
import javax.inject.Inject

class DeleteNoteByIdUseCaseImpl @Inject constructor(
    private val repository: NotesRepository
) : DeleteNoteByIdUseCase {
    override fun invoke(id: Long, onError: (Event.ErrorEvent) -> Unit) {
        try {
            repository.deleteNote(id = id)
        } catch (ex: Exception) {
            onError(Event.ErrorEvent)

            Log.e("Delete note exception", ex.stackTraceToString())
        }
    }
}