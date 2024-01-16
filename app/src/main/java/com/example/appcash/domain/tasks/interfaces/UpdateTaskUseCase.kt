package com.example.appcash.domain.tasks.interfaces

import com.example.appcash.data.entities.MainTask
import com.example.appcash.data.entities.Task

interface UpdateTaskUseCase {
    fun invoke(task: Task)
}