package com.example.appcash.domain.tasks.implementations

import com.example.appcash.data.entities.MainTask
import com.example.appcash.data.entities.SubTask
import com.example.appcash.data.repository_interfaces.TasksRepository
import com.example.appcash.domain.tasks.interfaces.GetMapTasksUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMapTasksUseCaseImpl @Inject constructor(
    private val repository: TasksRepository
): GetMapTasksUseCase {
    override fun invoke(): Flow<Map<MainTask, List<SubTask>?>> {
        return repository.getAllTasks()
    }
}