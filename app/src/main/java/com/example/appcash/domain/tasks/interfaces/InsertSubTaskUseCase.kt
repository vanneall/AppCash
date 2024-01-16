package com.example.appcash.domain.tasks.interfaces

import com.example.appcash.data.entities.SubTask

interface InsertSubTaskUseCase {
    fun invoke(mainId: Long, subTask: SubTask)
}