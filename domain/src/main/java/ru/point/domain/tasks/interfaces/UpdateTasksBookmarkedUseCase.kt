package ru.point.domain.tasks.interfaces

interface UpdateTasksBookmarkedUseCase {
    fun invoke(id: Long)
}