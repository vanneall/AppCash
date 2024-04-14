package com.example.appcash.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.appcash.navigation.Destinations.MAIN_CALENDAR_SCREEN
import com.example.appcash.navigation.screens.FinanceChartScreenNavigation
import com.example.appcash.navigation.screens.ErrorScreenNavigation
import com.example.appcash.navigation.screens.FinanceAddScreenNavigation
import com.example.appcash.navigation.screens.MainCalendarScreenNavigation
import com.example.appcash.navigation.screens.MainFinanceScreenNavigation
import com.example.appcash.navigation.screens.MainNotesScreenNavigation
import com.example.appcash.navigation.screens.MainTasksScreenNavigation
import com.example.appcash.navigation.screens.NoteInfoScreenNavigation
import com.example.appcash.navigation.screens.NotesListScreenNavigation
import com.example.appcash.navigation.screens.TasksListScreenNavigation
import com.example.appcash.view.FabState
import com.example.appcash.view.TopAppBarState


@Composable
fun AppCashNavHost(
    navHostController: NavHostController,
    topAppBarState: MutableState<TopAppBarState>,
    fabState: MutableState<FabState>,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navHostController,
        startDestination = MAIN_CALENDAR_SCREEN,
        modifier = modifier
    ) {
        MainNotesScreenNavigation(
            navGraphBuilder = this,
            navHostController = navHostController,
            topAppBarState = topAppBarState,
            fabState = fabState
        )
        NotesListScreenNavigation(
            navGraphBuilder = this,
            navHostController = navHostController,
            topAppBarState = topAppBarState,
            fabState = fabState
        )
        NoteInfoScreenNavigation(
            navGraphBuilder = this,
            navHostController = navHostController,
            topAppBarState = topAppBarState
        )


        MainTasksScreenNavigation(
            navGraphBuilder = this,
            navHostController = navHostController,
            topAppBarState = topAppBarState,
            fabState = fabState
        )
        TasksListScreenNavigation(
            navGraphBuilder = this,
            navHostController = navHostController,
            topAppBarState = topAppBarState,
            fabState = fabState
        )


        MainCalendarScreenNavigation(
            navGraphBuilder = this,
            navHostController = navHostController,
            topAppBarState = topAppBarState
        )


        MainFinanceScreenNavigation(
            navGraphBuilder = this,
            navHostController = navHostController,
            topAppBarState = topAppBarState,
            fabState = fabState
        )
        FinanceAddScreenNavigation(
            navGraphBuilder = this,
            navHostController = navHostController,
            topAppBarState = topAppBarState,
            fabState = fabState
        )
        FinanceChartScreenNavigation(
            navGraphBuilder = this,
            navHostController = navHostController,
            topAppBarState = topAppBarState
        )

        ErrorScreenNavigation(
            navGraphBuilder = this,
            navHostController = navHostController
        )
    }
}
