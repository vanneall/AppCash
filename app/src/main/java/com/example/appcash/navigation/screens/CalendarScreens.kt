@file:Suppress("FunctionName")

package com.example.appcash.navigation.screens

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.appcash.navigation.Destinations
import com.example.appcash.view.calendar.AppCacheCalendar

fun MainCalendarScreenNavigation(
    navGraphBuilder: NavGraphBuilder,
    navHostController: NavHostController
) {
    navGraphBuilder.composable(
        route = Destinations.MAIN_CALENDAR_SCREEN
    ) {
        AppCacheCalendar()
    }
}