package com.example.appcash.data.entities

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
    val colorIndex: Int,

    @ColumnInfo(name = "type")
    val discriminator: Discriminator,

    @ColumnInfo(name = "icon")
    val icon: String
) {
    enum class Discriminator {
        TASKS,
        NOTES,
        FINANCES
    }
}
