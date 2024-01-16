package com.example.appcash.domain.tasks.interfaces

import com.example.appcash.data.entities.MainTask
import com.example.appcash.data.entities.SubTask
import kotlinx.coroutines.flow.Flow

interface GetMapTasksByFolderIdUseCase {
    fun invoke(id: Long): Flow<Map<MainTask, List<SubTask>?>>
}