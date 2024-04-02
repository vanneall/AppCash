package ru.point.domain.notes.interfaces

interface UpdateFolderUseCase {
    fun invoke(id: Long, name: String, colorIndex: Int)
}