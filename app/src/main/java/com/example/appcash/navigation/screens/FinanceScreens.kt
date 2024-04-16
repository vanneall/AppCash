@file:Suppress("FunctionName")

package com.example.appcash.navigation.screens

import androidx.compose.runtime.MutableState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.appcash.navigation.Destinations
import com.example.appcash.view.FabState
import com.example.appcash.view.TopAppBarState
import com.example.appcash.view.finance.chart.components.ChartScreenViewModel
import com.example.appcash.view.finance.chart.screen.FinanceChartScreen
import com.example.appcash.view.finance.main.components.FinanceViewModel
import com.example.appcash.view.finance.main.screen.MainFinanceScreen
import com.example.appcash.view.finance.newfinance.components.AddFinanceViewModel
import com.example.appcash.view.finance.newfinance.screen.FinanceAddScreen

fun MainFinanceScreenNavigation(
    navGraphBuilder: NavGraphBuilder,
    navHostController: NavHostController,
    topAppBarState: MutableState<TopAppBarState>,
    fabState: MutableState<FabState>
) {
    navGraphBuilder.composable(
        route = Destinations.MAIN_FINANCE_SCREEN
    ) {
        val viewModel: FinanceViewModel = hiltViewModel()

        MainFinanceScreen(
            viewModel = viewModel,
            navigateTo = navHostController::navigate,
            topAppBarState = topAppBarState,
            fabState = fabState
        )
    }
}

fun FinanceAddScreenNavigation(
    navGraphBuilder: NavGraphBuilder,
    navHostController: NavHostController,
    topAppBarState: MutableState<TopAppBarState>,
    fabState: MutableState<FabState>
) {
    navGraphBuilder.composable(
        route = Destinations.FINANCE_ADD_SCREEN
    ) {
        val viewModel: AddFinanceViewModel = hiltViewModel()

        FinanceAddScreen(
            viewModel = viewModel,
            navigateTo = navHostController::navigate,
            navigateBack = navHostController::popBackStack,
            topAppBarState = topAppBarState,
            fabState = fabState,
        )
    }
}

fun FinanceChartScreenNavigation(
    navGraphBuilder: NavGraphBuilder,
    navHostController: NavHostController,
    topAppBarState: MutableState<TopAppBarState>,
    fabState: MutableState<FabState>
) {
    navGraphBuilder.composable(
        route = Destinations.FINANCE_CHART_SCREEN
    ) {
        val viewModel: ChartScreenViewModel = hiltViewModel()

        FinanceChartScreen(
            viewModel = viewModel,
            navigateBack = navHostController::popBackStack,
            topAppBarState = topAppBarState,
            fabState = fabState
        )
    }
}