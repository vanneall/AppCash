package com.example.appcash.domain.tasks.implementations

import com.example.appcash.data.entities.TaskWithTask
import com.example.appcash.data.repository_interfaces.TasksRepository
import com.example.appcash.domain.tasks.interfaces.GetTasksUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTaskUseCase @Inject constructor(
    private val repository: TasksRepository
) : GetTasksUseCase {
    override fun invoke(
        folderId: Long?,
    ): Flow<List<TaskWithTask>> {
        return if (folderId != null) repository.getTasks(folderId) else repository.getTasks()
    }
}