package com.example.appcash.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.appcash.R
import com.example.appcash.navigation.AppCashNavHost
import com.example.appcash.navigation.Destinations
import com.example.appcash.view.finance.screen.Finance
import com.example.appcash.view.finance.screen.FinanceScreen
import com.example.appcash.view.tasks.task.screen.TaskListScreen
import com.example.appcash.view.ui.theme.AppCashTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            AppCashTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FinanceScreen()
//                    Scaffold(
//                        modifier = Modifier.fillMaxSize(),
//                        bottomBar = {
//                            AppCashBottomNavigation(
//                                navController = navController,
//                                screens = listOf(
//                                    Screen(
//                                        "Заметки",
//                                        Destinations.FOLDERS_SCREEN,
//                                        icon = painterResource(id = R.drawable.notes_icon)
//                                    ),
//                                    Screen(
//                                        "Задачник",
//                                        Destinations.ALL_TASKS_SCREEN,
//                                        icon = painterResource(id = R.drawable.some_icon)
//                                    ),
//                                    Screen(
//                                        "Календарь",
//                                        Destinations.CALENDAR_SCREEN,
//                                        icon = painterResource(id = R.drawable.kid_star)
//                                    )
//                                )
//                            )
//                        }
//                    ) { it
//                        AppCashNavHost(navController)
//                    }
                }
            }
        }
    }
}

@Composable
fun AppCashBottomNavigation(
    navController: NavController,
    screens: List<Screen>
){
    BottomNavigation(
        modifier = Modifier.fillMaxWidth()
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        screens.forEach { screen ->
            BottomNavigationItem(
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { ScreenIcon(
                    screen.name,
                    screen.icon
                ) },
            )
        }
    }
}

@Composable
fun ScreenIcon(
    name: String,
    painter: Painter
) {
    Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(painter = painter, contentDescription = null)
        Text(text = name)
    }
}

data class Screen(
    val name: String,
    val route: String,
    val icon: Painter
)