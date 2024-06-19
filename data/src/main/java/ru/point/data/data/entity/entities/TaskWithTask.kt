package ru.point.data.data.entity.entities

import androidx.room.ColumnInfo
import androidx.room.Relation
import com.google.gson.annotations.SerializedName

data class TaskWithTask(
    @SerializedName(value = "id")
    val id: Long,
    @SerializedName("text")
    val text: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("date")
    @ColumnInfo("date")
    var date: String = "",
    @SerializedName("is_completed")
    var isCompleted: Boolean,
    @SerializedName("task_id")
    val taskId: Long? = null,
    @SerializedName("folder_id")
    val folderId: Long? = null,
    @SerializedName("favourites")
    @ColumnInfo("is_bookmark")
    var isBookmarked: Boolean,
    @SerializedName("folder_name")
    val folderName: String? = "",
    @SerializedName("subtasks")
    @Relation(parentColumn = "id", entityColumn = "parent_id")
    val subtasks: List<Task>
)