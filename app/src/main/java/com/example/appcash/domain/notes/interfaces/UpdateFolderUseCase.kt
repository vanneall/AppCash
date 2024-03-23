package com.example.appcash.domain.notes.interfaces

interface UpdateFolderUseCase {
    fun invoke(id: Long, name: String, colorIndex: Int)
}