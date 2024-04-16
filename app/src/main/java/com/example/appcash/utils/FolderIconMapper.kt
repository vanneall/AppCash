package com.example.appcash.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.appcash.R
import ru.point.data.data.entity.entities.FolderIcon

object FolderIconMapper {

    @Composable
    fun mapToIcon(value: FolderIcon): Painter {
        return when (value) {
            FolderIcon.FLOWER -> painterResource(id = R.drawable.flower_folder_icon)
            FolderIcon.CAR -> painterResource(id = R.drawable.car_folder_icon)
            FolderIcon.ENTERTAINMENTS -> painterResource(id = R.drawable.entertainment_folder_icon)
            FolderIcon.GAME -> painterResource(id = R.drawable.game_folder_icon)
            FolderIcon.HEALTH -> painterResource(id = R.drawable.health_folder_icon)
            FolderIcon.MONEY -> painterResource(id = R.drawable.money_folder_icon)
            FolderIcon.PET -> painterResource(id = R.drawable.pet_folder_icon)
            FolderIcon.FASTFOOD -> painterResource(id = R.drawable.fastfood_folder_icon)
            FolderIcon.RESTAURANT -> painterResource(id = R.drawable.restaraunt_folder_icon)
            FolderIcon.SHOP -> painterResource(id = R.drawable.shop_folder_icon)
            FolderIcon.SERVICES -> painterResource(id = R.drawable.service_folder_icon)
            FolderIcon.SPORT -> painterResource(id = R.drawable.sport_folder_icon)
            FolderIcon.TECHNIC -> painterResource(id = R.drawable.technic_folder_icon)
            FolderIcon.TRANSPORT -> painterResource(id = R.drawable.transport_folder_icon)
            FolderIcon.TRAVEL -> painterResource(id = R.drawable.travel_folder_icon)
            FolderIcon.UNKNOWN -> painterResource(id = R.drawable.unknown_folder_icon)
        }
    }

    fun mapToFolderIcon(position: Int): FolderIcon {
        return when (position) {
            0 -> FolderIcon.CAR
            1 -> FolderIcon.TRANSPORT
            2 -> FolderIcon.RESTAURANT
            3 -> FolderIcon.TRAVEL
            4 -> FolderIcon.PET
            5 -> FolderIcon.FLOWER
            6 -> FolderIcon.SPORT
            7 -> FolderIcon.FASTFOOD
            8 -> FolderIcon.MONEY
            9 -> FolderIcon.ENTERTAINMENTS
            10 -> FolderIcon.GAME
            11 -> FolderIcon.HEALTH
            12 -> FolderIcon.TECHNIC
            13 -> FolderIcon.SERVICES
            14 -> FolderIcon.SHOP
            else -> FolderIcon.UNKNOWN
        }
    }

}