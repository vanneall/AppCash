package com.example.appcash.domain.tasks.implementations

import com.example.appcash.data.entities.MainTask
import com.example.appcash.data.entities.SubTask
import com.example.appcash.data.repository_interfaces.TasksRepository
import com.example.appcash.domain.tasks.interfaces.GetMapTasksByFolderIdUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMapTasksByFolderIdUseCaseImpl @Inject constructor(
    private val repository: TasksRepository
): GetMapTasksByFolderIdUseCase {
    override fun invoke(id: Long): Flow<Map<MainTask, List<SubTask>?>> {
        return repository.getAllTasksById(id = id)
    }
}