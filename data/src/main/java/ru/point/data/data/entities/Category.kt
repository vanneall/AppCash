package ru.point.data.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "category"
)
data class Category(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "color")
    val color: Int,

    @ColumnInfo(name = "type")
    val discriminator: Discriminator,

    @ColumnInfo(name = "icon")
    val icon: FolderIcon
) {
    enum class Discriminator {
        TASKS,
        NOTES,
        FINANCES
    }
}
