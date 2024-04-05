package ru.point.domain.tasks.interfaces

import ru.point.data.data.entities.Task

interface InsertTaskUseCase {
    operator fun invoke(
        task: Task
    )
}