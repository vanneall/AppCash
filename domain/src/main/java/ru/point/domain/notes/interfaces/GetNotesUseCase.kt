package ru.point.domain.notes.interfaces

import kotlinx.coroutines.flow.Flow
import ru.point.data.data.entity.entities.Note

interface GetNotesUseCase {
    fun invoke(folderId: Long?): Flow<List<Note>>
}