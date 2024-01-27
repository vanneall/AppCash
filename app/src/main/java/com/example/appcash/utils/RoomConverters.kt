package com.example.appcash.utils

import androidx.room.TypeConverter
import com.example.appcash.data.entities.FolderType
import java.time.YearMonth

class RoomConverters {
    @TypeConverter
    fun fromStringToFolderType(value: String): FolderType {
        return when(value) {
            FolderType.NOTES.name -> FolderType.NOTES
            FolderType.FINANCIAL.name -> FolderType.FINANCIAL
            else -> FolderType.TASKS
        }
    }

    @TypeConverter
    fun fromFolderTypeToString(value: FolderType): String {
        return when(value) {
            FolderType.NOTES -> FolderType.NOTES.name
            FolderType.FINANCIAL -> FolderType.FINANCIAL.name
            else -> FolderType.TASKS.name
        }
    }

    @TypeConverter
    fun fromLocalDateToString(value: YearMonth): String {
        return value.toString()
    }

    @TypeConverter
    fun fromLocalDateToString(value: String): YearMonth {
        return YearMonth.parse(value)
    }
}
