package com.example.appcash.domain.tasks.implementations

import android.util.Log
import com.example.appcash.data.repository_interfaces.TasksRepository
import com.example.appcash.domain.tasks.interfaces.UpdateTaskUseCase
import com.example.appcash.utils.events.Event
import javax.inject.Inject

class UpdateTaskUseCaseImpl @Inject constructor(
    private val repository: TasksRepository
) : UpdateTaskUseCase {
    override fun invoke(
        id: Long,
        isChecked: Boolean,
        onError: (Event.ErrorEvent) -> Unit
    ) {
        try {
            repository.updateTask(id = id, isChecked = isChecked)
        } catch (ex: Exception) {
            onError(Event.ErrorEvent)

            Log.e("Update checkbox exception", ex.stackTraceToString())
        }
    }
}