package ru.point.domain.notes.implementations

import ru.point.data.data.factory.NoteFactory
import ru.point.data.data.repository_interfaces.NotesRepository
import ru.point.domain.notes.interfaces.UpsertNoteUseCase
import javax.inject.Inject

class UpsertNoteUseCaseImpl @Inject constructor(
    private val noteFactory: NoteFactory,
    private val repository: NotesRepository,
) : UpsertNoteUseCase {

    override fun invoke(id: Long?, title: String, content: String, categoryId: Long?) {
        val handledTitle = title.trim()
        val handledContent = content.trim()

        if (handledTitle.isEmpty()) return

        val note = noteFactory.create(
            id = id,
            title = handledTitle,
            content = handledContent,
            categoryId = categoryId
        )
        repository.createNote(note = note)
    }
}