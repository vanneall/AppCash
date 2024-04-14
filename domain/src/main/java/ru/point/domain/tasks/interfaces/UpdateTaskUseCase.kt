package ru.point.domain.tasks.interfaces

import java.time.LocalDate

interface UpdateTaskUseCase {
    fun invoke(
        id: Long,
        text: String,
        description: String,
        date: LocalDate
    )
}