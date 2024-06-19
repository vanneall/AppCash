package ru.point.data.data.datasource.remote.api

import com.google.gson.annotations.SerializedName

data class TaskDto(
    @SerializedName("text")
    val text: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("is_completed")
    val isCompleted: Boolean,
    @SerializedName("task_id")
    val taskId: Long?,
    @SerializedName("folder_id")
    val folderId: Long?,
    @SerializedName("favourites")
    val isFavorite: Boolean,
    @SerializedName("date")
    val date: String
)

data class SUKAPADLA(
    @SerializedName(value = "id")
    val id: Long,
    @SerializedName("text")
    val text: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("is_completed")
    val isCompleted: Boolean,
    @SerializedName("task_id")
    val taskId: Long?,
    @SerializedName("folder_id")
    val folderId: Long,
    @SerializedName("favourites")
    val isBookmarked: Boolean,
    @SerializedName("folder_name")
    val folderName: String,
    @SerializedName("subtasks")
    val subtasks: Any?
)
