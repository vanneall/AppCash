@file:Suppress("FunctionName")

package com.example.appcash.navigation.screens

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.appcash.navigation.Destinations.ERROR_SCREEN
import com.example.appcash.view.general.ErrorScreen

fun ErrorScreenNavigation(
    navGraphBuilder: NavGraphBuilder,
    navHostController: NavHostController
) {
    navGraphBuilder.composable(
        route = ERROR_SCREEN

    ) {
        ErrorScreen()
    }
}