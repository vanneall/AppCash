package com.example.appcash.utils

import androidx.compose.ui.graphics.Color

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

    val colorsList
    get() = listOf(
        Color(0xFF86986A),
        Color(0xFF940B25),
        Color(0xFFC65911),
        Color(0xFF55828B),
        Color(0xFFC88B18),
        Color(0xFF9C1448),
        Color(0xFFBE95C4),
        Color(0xFFBC8A5F),
        Color(0xFF9CAEA9)
    )

    fun List<Color>.getSafety(i: Int): Color {
        if (i in indices) return this[i]
        return Color.Black
    }
}