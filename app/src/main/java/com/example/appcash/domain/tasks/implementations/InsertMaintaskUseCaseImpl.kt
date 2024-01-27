package com.example.appcash.domain.tasks.implementations

import android.util.Log
import com.example.appcash.data.entities.MainTask
import com.example.appcash.data.repository_interfaces.TasksRepository
import com.example.appcash.domain.tasks.interfaces.InsertMaintaskUseCase
import com.example.appcash.utils.events.Event
import javax.inject.Inject

class InsertMaintaskUseCaseImpl @Inject constructor(
    private val repository: TasksRepository
) : InsertMaintaskUseCase {
    override fun invoke(folderId: Long, text: String, onError: (Event.ErrorEvent) -> Unit) {
        try {
            when (folderId) {
                (0).toLong() -> repository.insertMainTask(
                    task = MainTask(text = text)
                )
                else -> repository.insertMainTransaction(
                    folderId = folderId,
                    task = MainTask(text = text)
                )
            }
        } catch (ex: Exception) {
            onError(Event.ErrorEvent)

            Log.e("Insert Maintask exception", ex.stackTraceToString())
        }
    }
}