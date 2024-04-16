package ru.point.domain.notes.implementations

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.point.data.data.factory.NoteFactory
import ru.point.data.data.datasource.repository.interfaces.NotesRepository
import ru.point.domain.notes.interfaces.UpsertNoteUseCase
import javax.inject.Inject

class UpsertNoteUseCaseImpl @Inject constructor(
    private val noteFactory: NoteFactory,
    private val repository: NotesRepository,
) : UpsertNoteUseCase {

    override fun invoke(id: Long?, title: String, content: String, categoryId: Long?) {
        CoroutineScope(Dispatchers.Default).launch {
            val handledTitle = title.trim()
            val handledContent = content.trim()

            if (handledTitle.isEmpty()) return@launch

            val note = noteFactory.create(
                id = id,
                title = handledTitle,
                content = handledContent,
                categoryId = categoryId
            )

            repository.upsert(note = note)
        }
    }
}