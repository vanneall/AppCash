package com.example.appcash.domain.tasks.interfaces

import com.example.appcash.data.entities.MainTask
import com.example.appcash.data.entities.SubTask
import kotlinx.coroutines.flow.Flow

interface GetMapTasksUseCase {
    fun invoke(): Flow<Map<MainTask, List<SubTask>?>>
}