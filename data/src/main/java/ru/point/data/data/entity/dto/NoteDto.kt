package ru.point.data.data.entity.dto

import com.google.gson.annotations.SerializedName
import ru.point.data.data.entity.entities.Note

data class NoteDto(
    @SerializedName(value = "title")
    val title: String,
    @SerializedName(value = "content")
    val content: String,
    @SerializedName(value = "folder_id")
    val folderId: Long? = null,
)

fun Note.toNoteDto(): NoteDto {
    return NoteDto(
        title = title,
        content = content,
        folderId = folderId
    )
}