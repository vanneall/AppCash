package ru.point.data.data

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.room.TypeConverter
import ru.point.data.data.entity.entities.Category.Discriminator
import ru.point.data.data.entity.entities.FolderIcon
import java.time.LocalDate

class RoomConverters {
    @TypeConverter
    fun fromStringToFolderType(value: String): Discriminator {
        return when (value) {
            Discriminator.Note.name -> Discriminator.Note
            Discriminator.Finance.name -> Discriminator.Finance
            else -> Discriminator.Task
        }
    }

    @TypeConverter
    fun fromFolderTypeToString(value: Discriminator): String {
        return when (value) {
            Discriminator.Note -> Discriminator.Note.name
            Discriminator.Finance -> Discriminator.Finance.name
            else -> Discriminator.Task.name
        }
    }

    @TypeConverter
    fun fromLocalDateToString(value: LocalDate): String {
        return value.toString()
    }

    @TypeConverter
    fun fromLocalDateToString(value: String): LocalDate {
        return LocalDate.parse(value)
    }

    @TypeConverter
    fun fromColorToInt(value: Color): Int {
        return value.toArgb()
    }

    @TypeConverter
    fun fromIntToColor(value: Int): Color {
        return Color(value)
    }

    @TypeConverter
    fun fromFolderIconToString(value: FolderIcon): String {
        return value.name
    }

    @TypeConverter
    fun fromStringToFolderIcon(value: String?): FolderIcon? {
        if (value == null) return null
        return when (value) {
            FolderIcon.CAR.name -> FolderIcon.CAR
            FolderIcon.ENTERTAINMENTS.name -> FolderIcon.ENTERTAINMENTS
            FolderIcon.TRAVEL.name -> FolderIcon.TRAVEL
            FolderIcon.TRANSPORT.name -> FolderIcon.TRANSPORT
            FolderIcon.SHOP.name -> FolderIcon.SHOP
            FolderIcon.RESTAURANT.name -> FolderIcon.RESTAURANT
            FolderIcon.PET.name -> FolderIcon.PET
            FolderIcon.FLOWER.name -> FolderIcon.FLOWER
            FolderIcon.SPORT.name -> FolderIcon.SPORT
            FolderIcon.FASTFOOD.name -> FolderIcon.FASTFOOD
            FolderIcon.MONEY.name -> FolderIcon.MONEY
            FolderIcon.GAME.name -> FolderIcon.GAME
            FolderIcon.HEALTH.name -> FolderIcon.HEALTH
            FolderIcon.TECHNIC.name -> FolderIcon.TECHNIC
            FolderIcon.SERVICES.name -> FolderIcon.SERVICES
            else -> FolderIcon.UNKNOWN
        }
    }
}
