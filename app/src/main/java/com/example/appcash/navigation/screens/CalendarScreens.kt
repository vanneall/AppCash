@file:Suppress("FunctionName")

package com.example.appcash.navigation.screens

import androidx.compose.runtime.MutableState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.appcash.navigation.Destinations
import com.example.appcash.view.TopAppBarState
import com.example.appcash.view.calendar.CalendarScreen

fun MainCalendarScreenNavigation(
    navGraphBuilder: NavGraphBuilder,
    navHostController: NavHostController,
    topAppBarState: MutableState<TopAppBarState>
) {
    navGraphBuilder.composable(
        route = Destinations.MAIN_CALENDAR_SCREEN
    ) {
        CalendarScreen(
            topAppBarState = topAppBarState
        )
    }
}