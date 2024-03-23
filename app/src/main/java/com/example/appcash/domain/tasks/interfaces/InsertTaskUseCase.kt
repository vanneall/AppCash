package com.example.appcash.domain.tasks.interfaces

interface InsertTaskUseCase {
    operator fun invoke(
        text: String,
        parentTaskId: Long? = null,
        folderId: Long? = null
    )
}