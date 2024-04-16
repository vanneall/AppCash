package ru.point.domain.tasks.interfaces

import java.time.LocalDate

interface InsertTaskUseCase {
    operator fun invoke(
        text: String,
        description: String,
        parentTaskId: Long?,
        categoryId: Long?,
        date: LocalDate
    )
}