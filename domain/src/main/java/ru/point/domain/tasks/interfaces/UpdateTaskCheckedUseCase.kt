package ru.point.domain.tasks.interfaces

interface UpdateTaskCheckedUseCase {
    fun invoke(id: Long, isChecked: Boolean)
}