package com.example.appcash.domain.tasks.implementations

import com.example.appcash.data.repository_interfaces.TasksRepository
import com.example.appcash.domain.tasks.interfaces.UpdateTaskUseCase
import javax.inject.Inject

class UpdateTaskUseCaseImpl @Inject constructor(
    private val repository: TasksRepository
) : UpdateTaskUseCase {
    override fun invoke(
        id: Long,
        isChecked: Boolean,
    ) {
        repository.updateTask(id = id, isChecked = isChecked)
    }
}