package ru.point.domain.notes.interfaces

interface UpsertNoteUseCase {
    fun invoke(
        id: Long?,
        title: String,
        content: String,
        categoryId: Long? = null,
    )
}