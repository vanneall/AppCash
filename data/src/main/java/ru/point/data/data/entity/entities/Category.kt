package ru.point.data.data.entity.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "category"
)
data class Category(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @SerializedName(value = "id")
    val id: Long = 0,

    @SerializedName(value = "name")
    @ColumnInfo(name = "name")
    val name: String,

    @SerializedName(value = "type")
    @ColumnInfo(name = "type")
    val discriminator: Discriminator,

    @SerializedName(value = "image")
    @ColumnInfo(name = "icon")
    val icon: FolderIcon,

    @SerializedName(value = "color")
    @ColumnInfo(name = "color")
    val color: Int,

    @SerializedName(value = "user_id")
    val userId: Int = 2,

    @SerializedName(value = "folder_count")
    val folderCount: Int = 1
) {
    enum class Discriminator {
        Task,
        Note,
        Finance
    }
}
