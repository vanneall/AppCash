package ru.point.domain.tasks.interfaces

interface DeleteTaskByIdUseCase {
    fun invoke(id: Long)
}