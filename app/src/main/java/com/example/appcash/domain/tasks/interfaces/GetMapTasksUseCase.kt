package com.example.appcash.domain.tasks.interfaces

import com.example.appcash.data.entities.MainTask
import com.example.appcash.data.entities.SubTask
import com.example.appcash.utils.events.Event
import kotlinx.coroutines.flow.Flow

interface GetMapTasksUseCase {
    fun invoke( onError: (Event.ErrorEvent) -> Unit): Flow<Map<MainTask, List<SubTask>?>>
}