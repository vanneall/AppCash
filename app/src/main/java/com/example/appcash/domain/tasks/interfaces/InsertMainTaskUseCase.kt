package com.example.appcash.domain.tasks.interfaces

import com.example.appcash.data.entities.MainTask

interface InsertMainTaskUseCase {
    operator fun invoke(folderId: Long?, mainTask: MainTask)
}