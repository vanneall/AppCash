package com.example.appcash.domain.tasks.implementations

import com.example.appcash.data.entities.MainTask
import com.example.appcash.data.repository_interfaces.TasksRepository
import com.example.appcash.domain.tasks.interfaces.InsertMainTaskUseCase
import javax.inject.Inject

class InsertMainTaskUseCaseImpl @Inject constructor(
    private val repository: TasksRepository
): InsertMainTaskUseCase {
    override fun invoke(folderId: Long?, mainTask: MainTask) {
        when (folderId) {
            null -> repository.insertMainTask(mainTask = mainTask)
            else -> repository.insertMainTransaction(folderId, mainTask)
        }
    }
}