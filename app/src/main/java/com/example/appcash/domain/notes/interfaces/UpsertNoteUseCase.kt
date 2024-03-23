package com.example.appcash.domain.notes.interfaces

interface UpsertNoteUseCase {
    fun invoke(
        title: String,
        content: String,
        folderId: Long? = null,
    )

    fun invoke(
        id: Long,
        title: String,
        content: String,
        folderId: Long? = null,
    )
}