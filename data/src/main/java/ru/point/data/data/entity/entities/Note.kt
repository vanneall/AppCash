package ru.point.data.data.entity.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "note",
    foreignKeys = [ForeignKey(
        entity = Category::class,
        parentColumns = ["id"],
        childColumns = ["category_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Note(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @SerializedName(value = "id")
    val id: Long = 0,

    @SerializedName(value = "title")
    @ColumnInfo(name = "title")
    val title: String,

    @SerializedName(value = "content")
    @ColumnInfo(name = "content")
    val content: String,

    @SerializedName(value = "folder_id")
    @ColumnInfo("category_id")
    val folderId: Long? = null,

    @SerializedName(value = "folder_name")
    val folderName: String = ""
)
