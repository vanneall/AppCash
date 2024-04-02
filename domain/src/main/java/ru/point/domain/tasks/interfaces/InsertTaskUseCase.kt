package ru.point.domain.tasks.interfaces

interface InsertTaskUseCase {
    operator fun invoke(
        text: String,
        parentTaskId: Long? = null,
        folderId: Long? = null
    )
}