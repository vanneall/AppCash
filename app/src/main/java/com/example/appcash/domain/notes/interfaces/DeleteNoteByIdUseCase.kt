package com.example.appcash.domain.notes.interfaces

interface DeleteNoteByIdUseCase {
    fun invoke(id: Long)
}