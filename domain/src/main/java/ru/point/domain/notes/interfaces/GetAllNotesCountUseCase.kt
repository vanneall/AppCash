package ru.point.domain.notes.interfaces

import kotlinx.coroutines.flow.Flow

interface GetAllNotesCountUseCase {
    fun invoke(): Flow<Int>
}