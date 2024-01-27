package com.example.appcash.domain.notes.interfaces

import com.example.appcash.utils.events.Event.ErrorEvent
import kotlinx.coroutines.flow.Flow

interface GetFolderNameByIdUseCase {
    fun invoke(id: Long, onError: (ErrorEvent) -> Unit): Flow<String>
}