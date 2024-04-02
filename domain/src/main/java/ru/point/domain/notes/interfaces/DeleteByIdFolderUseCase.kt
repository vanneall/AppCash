package ru.point.domain.notes.interfaces

interface DeleteByIdFolderUseCase {
    fun invoke(id: Long)
}