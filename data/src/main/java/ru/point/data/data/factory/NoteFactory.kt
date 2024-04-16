package ru.point.data.data.factory

import ru.point.data.data.entities.Note

interface NoteFactory {
    fun create(id: Long?, title: String, content: String, categoryId: Long?): Note
}