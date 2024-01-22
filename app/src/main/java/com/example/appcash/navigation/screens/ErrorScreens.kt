package com.example.appcash.navigation.screens

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.appcash.navigation.Destinations.ERROR_SCREEN
import com.example.appcash.utils.ArgsKeys.ERROR_MESSAGE_KEY
import com.example.appcash.view.general.ErrorScreen

fun ErrorScreenNavigation(
    navGraphBuilder: NavGraphBuilder,
    navHostController: NavHostController
) {
    navGraphBuilder.composable(
        route = "$ERROR_SCREEN/$ERROR_MESSAGE_KEY",

        arguments = listOf(
            navArgument(
                name = ERROR_MESSAGE_KEY
            ) {
                type = NavType.StringType
            }
        )
    ) {backStackEntry ->
        val message = backStackEntry.arguments?.getString(ERROR_MESSAGE_KEY) ?: ""
        ErrorScreen(message = message)
    }
}