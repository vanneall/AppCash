package com.example.appcash.domain.tasks.implementations

import com.example.appcash.data.entities.Task
import com.example.appcash.data.repository_interfaces.TasksRepository
import com.example.appcash.domain.tasks.interfaces.InsertTaskUseCase
import javax.inject.Inject

class InsertTaskUseCaseImpl @Inject constructor(
    private val repository: TasksRepository
) : InsertTaskUseCase {
    override fun invoke(
        text: String,
        parentTaskId: Long?,
        folderId: Long?
    ) {
        val task = Task(
            text = text,
            isCompleted = false,
            parentId = parentTaskId,
            folderId = folderId
        )
        repository.createTask(task)
    }
}