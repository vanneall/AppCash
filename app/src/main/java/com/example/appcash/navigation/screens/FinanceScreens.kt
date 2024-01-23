@file:Suppress("FunctionName")

package com.example.appcash.navigation.screens

import androidx.compose.runtime.MutableState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.appcash.navigation.Destinations
import com.example.appcash.view.TopAppBarState
import com.example.appcash.view.finance.add_folder.components.AddFolderViewModel
import com.example.appcash.view.finance.add_folder.screen.CreatingFinanceFolderScreen
import com.example.appcash.view.finance.add_screen.components.AddFinanceViewModel
import com.example.appcash.view.finance.add_screen.screen.FinanceAccountingScreen
import com.example.appcash.view.finance.main_screen.components.FinanceViewModel
import com.example.appcash.view.finance.main_screen.screen.MainFinanceScreen

fun MainFinanceScreenNavigation(
    navGraphBuilder: NavGraphBuilder,
    navHostController: NavHostController,
    topAppBarState: MutableState<TopAppBarState>
) {
    navGraphBuilder.composable(
        route = Destinations.MAIN_FINANCE_SCREEN
    ) {
        val viewModel: FinanceViewModel = hiltViewModel()
        MainFinanceScreen(
            viewModel = viewModel,
            navigateTo = navHostController::navigate,
            topAppBarState = topAppBarState
        )
    }
}

fun FinanceAccountingScreenNavigation(
    navGraphBuilder: NavGraphBuilder,
    navHostController: NavHostController,
    topAppBarState: MutableState<TopAppBarState>
) {
    navGraphBuilder.composable(
        route = Destinations.FINANCE_ACCOUNTING_SCREEN
    ) {
        val viewModel: AddFinanceViewModel = hiltViewModel()
        FinanceAccountingScreen(
            viewModel = viewModel,
            navigateTo = navHostController::navigate,
            navigateBack = navHostController::popBackStack,
            topAppBarState = topAppBarState
        )
    }
}

fun CreatingFinanceFolderScreenNavigation(
    navGraphBuilder: NavGraphBuilder,
    navHostController: NavHostController,
    topAppBarState: MutableState<TopAppBarState>
) {
    navGraphBuilder.composable(
        route = Destinations.CREATING_FINANCE_FOLDER_SCREEN
    ) {
        val viewModel: AddFolderViewModel = hiltViewModel()
        CreatingFinanceFolderScreen(
            viewModel = viewModel,
            navigateBack = navHostController::popBackStack,
            topAppBarState = topAppBarState,
        )
    }
}