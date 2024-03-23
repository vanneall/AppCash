package com.example.appcash.domain.tasks.implementations

import android.util.Log
import com.example.appcash.data.entities.Task
import com.example.appcash.data.repository_interfaces.TasksRepository
import com.example.appcash.domain.tasks.interfaces.InsertMaintaskUseCase
import com.example.appcash.utils.events.Event
import javax.inject.Inject

class InsertMaintaskUseCaseImpl @Inject constructor(
    private val repository: TasksRepository
) : InsertMaintaskUseCase {
    override fun invoke(
        text: String,
        parentTaskId: Long?,
        folderId: Long?,
        onError: (Event.ErrorEvent) -> Unit
    ) {
        try {
            val task = Task(
                text = text,
                isCompleted = false,
                parentId = parentTaskId,
                folderId = folderId
            )
            repository.createTask(task)
        } catch (ex: Exception) {
            onError(Event.ErrorEvent)

            Log.e("Insert Maintask exception", ex.stackTraceToString())
        }
    }
}