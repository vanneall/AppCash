package ru.point.data.data.factory

import ru.point.data.data.entities.Task
import java.time.LocalDate

interface TaskFactory {
    fun create(
        text: String,
        description: String?,
        parentTaskId: Long?,
        date: LocalDate,
        categoryId: Long?
    ): Task
}