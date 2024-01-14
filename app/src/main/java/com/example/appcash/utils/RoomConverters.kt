package com.example.appcash.utils

import androidx.room.TypeConverter
import com.example.appcash.data.entities.FolderType

class RoomConverters {
    @TypeConverter
    fun fromStringToFolderType(value: String): FolderType {
        return when(value) {
            FolderType.NOTES.name -> FolderType.NOTES
            else -> FolderType.TASKS
        }
    }

    @TypeConverter
    fun fromFolderTypeToString(value: FolderType): String {
        return when(value) {
            FolderType.NOTES -> FolderType.NOTES.name
            else -> FolderType.TASKS.name
        }
    }
}
