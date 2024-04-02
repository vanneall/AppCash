package com.example.appcash.utils

import androidx.compose.ui.graphics.Color
import com.example.appcash.view.ui.theme.DarkBlue
import com.example.appcash.view.ui.theme.DarkRed
import com.example.appcash.view.ui.theme.DarkTurquoise
import com.example.appcash.view.ui.theme.Orange

object ParamsStore {
    val icons
        get() = listOf(
            "alcohol_folder_icon",
            "car_folder_icon",
            "fastfood_folder_icon",
            "finance_folder_icon",
            "flower_folder_icon",
            "food_cart_folder_icon",
            "game_folder_icon",
            "home_technic_folder_icon",
            "household_folder_icon",
            "medicine_folder_icon",
            "pets_folder_icon",
            "savings_folder_icon",
            "shopping_folder_icon",
            "sport_folder_icon",
            "technic_folder_icon",
            "theater_folder_icon",
            "travel_folder_icon"
        )

    val colorsList: List<Color>
        get() = listOf(
            DarkRed,
            DarkBlue,
            Orange,
            DarkTurquoise
        )
}