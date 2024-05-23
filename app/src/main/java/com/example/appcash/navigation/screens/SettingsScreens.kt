package com.example.appcash.navigation.screens

import androidx.compose.runtime.MutableState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.appcash.navigation.Destinations
import com.example.appcash.view.FabState
import com.example.appcash.view.TopAppBarState
import com.example.appcash.view.settings.currancy.component.CurrencySelectViewModel
import com.example.appcash.view.settings.currancy.screen.CurrencySelectScreenController
import com.example.appcash.view.settings.main.screen.SettingsScreen

fun MainSettingScreen(
    navGraphBuilder: NavGraphBuilder,
    navHostController: NavHostController,
    topAppBarState: MutableState<TopAppBarState>,
    fabState: MutableState<FabState>
) {
    navGraphBuilder.composable(
        route = Destinations.MAIN_SETTINGS_SCREEN
    ) {
        SettingsScreen(
            topAppBarState = topAppBarState,
            fabState = fabState,
            navigateBack = navHostController::popBackStack,
            navigateTo = navHostController::navigate
        )
    }
}

fun CurrencySelectsScreen(
    navGraphBuilder: NavGraphBuilder,
    navHostController: NavHostController,
    topAppBarState: MutableState<TopAppBarState>,
    fabState: MutableState<FabState>
) {
    navGraphBuilder.composable(
        route = Destinations.SETTINGS_CURRENCY_SCREEN
    ) {
        val viewModel: CurrencySelectViewModel = hiltViewModel()

        CurrencySelectScreenController(
            viewModel = viewModel,
            topAppBarState = topAppBarState,
            fabState = fabState,
            navigateBack = navHostController::popBackStack,
        )
    }
}