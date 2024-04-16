package ru.point.data.data.factory

import ru.point.data.data.entity.entities.Task
import java.time.LocalDate

class TaskFactoryImpl : TaskFactory {
    override fun create(
        text: String,
        description: String?,
        parentTaskId: Long?,
        date: LocalDate,
        categoryId: Long?
    ): Task {
        return Task(
            text = text,
            isCompleted = false,
            isBookmark = false,
            description = description ?: "",
            parentTaskId = parentTaskId,
            categoryId = categoryId,
            date = date
        )
    }
}