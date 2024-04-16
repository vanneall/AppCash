package ru.point.domain.category.interfaces

interface UpdateFolderUseCase {
    fun invoke(id: Long, name: String, colorIndex: Int)
}