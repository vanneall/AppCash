package com.example.appcash.domain.tasks.implementations

import android.util.Log
import com.example.appcash.data.repository_interfaces.TasksRepository
import com.example.appcash.domain.tasks.interfaces.UpdateTaskUseCase
import com.example.appcash.utils.events.Event
import com.example.appcash.view.tasks.task.components.TaskType
import javax.inject.Inject

class UpdateTaskUseCaseImpl @Inject constructor(
    private val repository: TasksRepository
) : UpdateTaskUseCase {
    override fun invoke(
        id: Long,
        isChecked: Boolean,
        type: TaskType,
        onError: (Event.ErrorEvent) -> Unit
    ) {
        try {
            when (type) {
                TaskType.MAIN -> {
                    repository.updateMaintask(id = id, isChecked = isChecked)
                }
                TaskType.SUB -> {
                    repository.updateSubtask(id = id, isChecked = isChecked)
                }
            }
        } catch (ex: Exception) {
            onError(Event.ErrorEvent)

            Log.e("Update checkbox exception", ex.stackTraceToString())
        }
    }
}