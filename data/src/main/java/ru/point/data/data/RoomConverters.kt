package ru.point.data.data

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.room.TypeConverter
import ru.point.data.data.entities.Category.Discriminator
import java.time.YearMonth

class RoomConverters {
    @TypeConverter
    fun fromStringToFolderType(value: String): Discriminator {
        return when(value) {
            Discriminator.NOTES.name -> Discriminator.NOTES
            Discriminator.FINANCES.name -> Discriminator.FINANCES
            else -> Discriminator.TASKS
        }
    }

    @TypeConverter
    fun fromFolderTypeToString(value: Discriminator): String {
        return when(value) {
            Discriminator.NOTES -> Discriminator.NOTES.name
            Discriminator.FINANCES -> Discriminator.FINANCES.name
            else -> Discriminator.TASKS.name
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

    @TypeConverter
    fun fromColorToInt(value: Color): Int {
        return value.toArgb()
    }

    @TypeConverter
    fun fromIntToColor(value: Int): Color {
        return Color(value)
    }
}
