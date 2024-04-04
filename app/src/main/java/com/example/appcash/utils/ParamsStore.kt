package com.example.appcash.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.appcash.R
import com.example.appcash.view.ui.theme.DarkBlue
import com.example.appcash.view.ui.theme.DarkRed
import com.example.appcash.view.ui.theme.DarkTurquoise
import com.example.appcash.view.ui.theme.Orange

object ParamsStore {

    val iconsList: List<Painter>
        @Composable
        get() = listOf(
            painterResource(id = R.drawable.car_folder_icon),
            painterResource(id = R.drawable.transport_folder_icon),
            painterResource(id = R.drawable.restaraunt_folder_icon),
            painterResource(id = R.drawable.travel_folder_icon),
            painterResource(id = R.drawable.pet_folder_icon),
            painterResource(id = R.drawable.flower_folder_icon),
            painterResource(id = R.drawable.sport_folder_icon),
            painterResource(id = R.drawable.fastfood_folder_icon),
            painterResource(id = R.drawable.money_folder_icon),
            painterResource(id = R.drawable.entertainment_folder_icon),
            painterResource(id = R.drawable.game_folder_icon),
            painterResource(id = R.drawable.health_folder_icon),
            painterResource(id = R.drawable.technic_folder_icon),
            painterResource(id = R.drawable.service_folder_icon),
            painterResource(id = R.drawable.shop_folder_icon),
        )

    val colorsList: List<Color>
        get() = listOf(
            DarkRed,
            DarkBlue,
            Orange,
            DarkTurquoise
        )
}