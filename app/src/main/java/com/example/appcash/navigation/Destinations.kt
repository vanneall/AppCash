package com.example.appcash.navigation

import com.example.appcash.R

object Destinations {
    const val MAIN_NOTES_FOLDER_SCREEN = "MAIN_NOTES_FOLDER_SCREEN"

    const val NOTES_LIST_SCREEN = "NOTES_LIST_SCREEN"

    const val NOTE_SCREEN = "NOTE_SCREEN"

    const val MAIN_TASKS_FOLDER_SCREEN = "MAIN_TASKS_SCREEN"

    const val TASKS_SCREEN = "TASKS_SCREEN"

    const val MAIN_CALENDAR_SCREEN = "MAIN_CALENDAR_SCREEN"

    const val MAIN_FINANCE_SCREEN = "MAIN_FINANCE_SCREEN"

    const val CREATING_FINANCE_FOLDER_SCREEN = "CREATING_FINANCE_FOLDER_SCREEN"

    const val FINANCE_ACCOUNTING_SCREEN = "FINANCE_ACCOUNTING_SCREEN"

    const val ERROR_SCREEN = "ERROR_SCREEN"
}

val screenBottomItems = listOf(
    Screen(
        "Заметки",
        Destinations.MAIN_NOTES_FOLDER_SCREEN,
        iconId = R.drawable.notes_icon
    ),
    Screen(
        "Задачник",
        Destinations.MAIN_TASKS_FOLDER_SCREEN,
        iconId = R.drawable.some_icon
    ),
    Screen(
        "Календарь",
        Destinations.MAIN_CALENDAR_SCREEN,
        iconId = R.drawable.kid_star
    ),
    Screen(
        "Финансы",
        Destinations.MAIN_FINANCE_SCREEN,
        iconId = R.drawable.chart_icon
    )
)

data class Screen(
    val name: String,
    val route: String,
    val iconId: Int
)