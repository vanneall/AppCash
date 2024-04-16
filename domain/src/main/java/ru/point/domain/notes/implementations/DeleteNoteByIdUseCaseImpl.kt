package ru.point.domain.notes.implementations

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.point.data.data.datasource.repository.interfaces.NotesRepository
import ru.point.domain.notes.interfaces.DeleteNoteByIdUseCase
import javax.inject.Inject

class DeleteNoteByIdUseCaseImpl @Inject constructor(
    private val repository: NotesRepository
) : DeleteNoteByIdUseCase {
    override fun invoke(id: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.delete(id = id)
        }
    }
}