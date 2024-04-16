package ru.point.data.data.factory

import ru.point.data.data.entity.entities.Note

class NoteFactoryImpl : NoteFactory {
    override fun create(
        id: Long?, title: String, content: String, categoryId: Long?
    ): Note {
        return Note(
            id = id ?: 0,
            title = title,
            content = content,
            folderId = categoryId,
        )
    }
}