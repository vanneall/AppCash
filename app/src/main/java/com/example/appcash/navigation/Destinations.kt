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

    const val ADD_FINANCE_OPERATION_SCREEN = "ADD_FINANCE_OPERATION_SCREEN"
}

val screenBottomItems = listOf(
    Screen(
        Destinations.MAIN_CALENDAR_SCREEN,
        iconId = R.drawable.calendar_nav_icon
    ),
    Screen(
        Destinations.MAIN_FINANCE_SCREEN,
        iconId = R.drawable.chart_nav_icon
    ),

    //Заглушка для красивого распределения иконок
    Screen(
        Destinations.MAIN_NOTES_FOLDER_SCREEN,
        iconId = R.drawable.notes_icon
    ),

    Screen(
        Destinations.MAIN_TASKS_FOLDER_SCREEN,
        iconId = R.drawable.tasks_nav_icon
    ),

    Screen(
        Destinations.MAIN_NOTES_FOLDER_SCREEN,
        iconId = R.drawable.note_nav_icon
    ),
)

data class Screen(
    val route: String,
    val iconId: Int
)