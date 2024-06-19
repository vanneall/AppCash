package ru.point.data.data.entity.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName

//Используется вместе с TaskWithTask, потому как у каждой Task может быть своя Task
@Entity(
    tableName = "task",
    foreignKeys = [ForeignKey(
        entity = Category::class,
        parentColumns = ["id"],
        childColumns = ["category_id"],
        onDelete = ForeignKey.CASCADE
    ),
        ForeignKey(
            entity = Task::class,
            parentColumns = ["id"],
            childColumns = ["parent_id"],
            onDelete = ForeignKey.CASCADE
        )]
)
@TypeConverters(Converter::class)
data class Task(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    @SerializedName(value = "id")
    val id: Long = 0,
    @ColumnInfo("text")
    @SerializedName("text")
    val text: String,
    @ColumnInfo("isCompleted")
    @SerializedName("is_completed")
    var isCompleted: Boolean = false,
    @ColumnInfo("description")
    @SerializedName("description")
    val description: String = "",
    @ColumnInfo("parent_id")
    @SerializedName("task_id")
    val parentTaskId: Long? = null,
    @ColumnInfo("date")
    @SerializedName("date")
    val date: String? = null,
    @ColumnInfo("category_id")
    @SerializedName("folder_id")
    val categoryId: Long? = null,
    @ColumnInfo("is_bookmark")
    @SerializedName("favourites")
    val isBookmark: Boolean = false,
    @SerializedName("subtasks")
    val subtasks: List<Task>? = null
)

class Converter() {
    @TypeConverter
    fun subToString(subtasks: List<Task>?): String {
        return ""
    }
    @TypeConverter
    fun strToSub(str: String): List<Task>? {
        return null
    }
}