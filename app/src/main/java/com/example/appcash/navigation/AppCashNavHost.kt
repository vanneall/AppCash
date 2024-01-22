package com.example.appcash.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.appcash.navigation.Destinations.MAIN_TASKS_FOLDER_SCREEN
import com.example.appcash.navigation.exceptions.MismatchOpenModeException
import com.example.appcash.navigation.screens.CreatingFinanceFolderScreenNavigation
import com.example.appcash.navigation.screens.ErrorScreenNavigation
import com.example.appcash.navigation.screens.FinanceAccountingScreenNavigation
import com.example.appcash.navigation.screens.MainCalendarScreenNavigation
import com.example.appcash.navigation.screens.MainFinanceScreenNavigation
import com.example.appcash.navigation.screens.MainNotesScreenNavigation
import com.example.appcash.navigation.screens.MainTasksScreenNavigation
import com.example.appcash.navigation.screens.NoteScreenNavigation
import com.example.appcash.navigation.screens.NotesListScreenNavigation
import com.example.appcash.navigation.screens.TasksScreenNavigation
import com.example.appcash.utils.ArgsKeys


@Composable
fun AppCashNavHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = MAIN_TASKS_FOLDER_SCREEN,
    ) {
        MainNotesScreenNavigation(
            navGraphBuilder = this,
            navHostController = navHostController
        )
        NotesListScreenNavigation(
            navGraphBuilder = this,
            navHostController = navHostController
        )
        NoteScreenNavigation(
            navGraphBuilder = this,
            navHostController = navHostController
        )


        MainTasksScreenNavigation(
            navGraphBuilder = this,
            navHostController = navHostController
        )
        TasksScreenNavigation(
            navGraphBuilder = this,
            navHostController = navHostController
        )


        MainCalendarScreenNavigation(
            navGraphBuilder = this,
            navHostController = navHostController
        )


        MainFinanceScreenNavigation(
            navGraphBuilder = this,
            navHostController = navHostController
        )
        FinanceAccountingScreenNavigation(
            navGraphBuilder = this,
            navHostController = navHostController
        )
        CreatingFinanceFolderScreenNavigation(
            navGraphBuilder = this,
            navHostController = navHostController
        )

        ErrorScreenNavigation(
            this,
            navHostController
        )
    }
}
