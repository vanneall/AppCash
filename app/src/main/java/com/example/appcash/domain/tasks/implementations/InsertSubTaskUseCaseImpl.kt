package com.example.appcash.domain.tasks.implementations

import com.example.appcash.data.entities.SubTask
import com.example.appcash.data.repository_interfaces.TasksRepository
import com.example.appcash.domain.tasks.interfaces.InsertSubTaskUseCase
import javax.inject.Inject

class InsertSubTaskUseCaseImpl @Inject constructor(
    private val repository: TasksRepository
): InsertSubTaskUseCase {
    override operator fun invoke(mainId: Long, subTask: SubTask) {
        repository.insertSubTask(mainId = mainId, subTask = subTask)
    }
}