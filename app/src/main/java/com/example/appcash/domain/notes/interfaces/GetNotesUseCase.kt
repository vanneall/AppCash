package com.example.appcash.domain.notes.interfaces

import com.example.appcash.data.entities.Note
import com.example.appcash.utils.events.Event.ErrorEvent
import kotlinx.coroutines.flow.Flow

interface GetNotesUseCase {

    fun invoke(onError: (ErrorEvent) -> Unit): Flow<List<Note>>

}