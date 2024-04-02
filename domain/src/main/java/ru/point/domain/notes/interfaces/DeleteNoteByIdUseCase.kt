package ru.point.domain.notes.interfaces

interface DeleteNoteByIdUseCase {
    fun invoke(id: Long)
}