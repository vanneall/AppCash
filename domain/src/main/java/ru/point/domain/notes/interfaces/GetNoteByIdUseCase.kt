package ru.point.domain.notes.interfaces

import kotlinx.coroutines.flow.Flow
import ru.point.data.data.entity.entities.Note

interface GetNoteByIdUseCase {
    fun invoke(id: Long): Flow<Note>
}