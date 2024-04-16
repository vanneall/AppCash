@file:Suppress("FunctionName")

package com.example.appcash.navigation.screens

import android.app.Activity
import androidx.compose.runtime.MutableState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.appcash.navigation.Destinations
import com.example.appcash.utils.ArgsKeys
import com.example.appcash.view.FabState
import com.example.appcash.view.TopAppBarState
import com.example.appcash.view.finance.chart.components.ChartScreenViewModel
import com.example.appcash.view.finance.chart.components.ChartViewModelFactoryProvider
import com.example.appcash.view.finance.chart.components.OpenMode
import com.example.appcash.view.finance.chart.screen.FinanceChartScreen
import com.example.appcash.view.finance.main.components.FinanceViewModel
import com.example.appcash.view.finance.main.screen.MainFinanceScreen
import com.example.appcash.view.finance.newfinance.components.AddFinanceViewModel
import com.example.appcash.view.finance.newfinance.screen.FinanceAddScreen
import dagger.hilt.android.EntryPointAccessors

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
        route = "${Destinations.FINANCE_CHART_SCREEN}/{${ArgsKeys.OPEN_MODE_KEY}}",
        arguments = listOf(
            navArgument(name = ArgsKeys.OPEN_MODE_KEY) {
                type = NavType.StringType
            }
        )
    ) { backStackEntry ->


        val assistedFactory = EntryPointAccessors.fromActivity(
            activity = LocalContext.current as Activity,
            entryPoint = ChartViewModelFactoryProvider::class.java
        ).provideChartViewModelAssistedFactory()

        val openModeHandled: OpenMode
        with(backStackEntry) {
            val openMode = arguments?.getString(ArgsKeys.OPEN_MODE_KEY)
            openModeHandled = OpenMode.handle(openMode)
        }

        val viewModel: ChartScreenViewModel = viewModel {
            assistedFactory.create(openModeHandled)
        }

        FinanceChartScreen(
            viewModel = viewModel,
            navigateBack = navHostController::popBackStack,
            topAppBarState = topAppBarState,
            fabState = fabState
        )
    }
}