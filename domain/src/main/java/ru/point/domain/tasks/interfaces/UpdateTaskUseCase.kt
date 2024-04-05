package ru.point.domain.tasks.interfaces

interface UpdateTaskUseCase {
    fun invoke(
        id: Long,
        text: String,
        description: String
    )
}