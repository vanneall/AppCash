package com.example.appcash.domain.tasks.implementations

import android.util.Log
import com.example.appcash.data.entities.SubTask
import com.example.appcash.data.repository_interfaces.TasksRepository
import com.example.appcash.domain.tasks.interfaces.InsertSubtaskUseCase
import com.example.appcash.utils.events.Event
import javax.inject.Inject

class InsertSubtaskUseCaseImpl @Inject constructor(
    private val repository: TasksRepository
) : InsertSubtaskUseCase {
    override operator fun invoke(
        mainTaskId: Long,
        text: String,
        onError: (Event.ErrorEvent) -> Unit
    ) {
        try {
            repository.insertSubTask(
                mainId = mainTaskId,
                subTask = SubTask(
                    text = text
                )
            )
        } catch (ex: Exception) {
            onError(Event.ErrorEvent)

            Log.e("Insert Subtask exception", ex.stackTraceToString())
        }
    }
}