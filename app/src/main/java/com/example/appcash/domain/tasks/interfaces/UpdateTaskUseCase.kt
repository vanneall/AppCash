package com.example.appcash.domain.tasks.interfaces

interface UpdateTaskUseCase {
    fun invoke(
        id: Long,
        isChecked: Boolean,
    )
}