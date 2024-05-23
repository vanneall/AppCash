package com.example.appcash.navigation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.appcash.view.ui.theme.Gray

@Composable
fun AppCashBottomNavigation(
    navController: NavController,
    screens: List<Screen>,
    modifier: Modifier = Modifier
) {
    BottomNavigation(
        modifier = modifier,
        backgroundColor = Color.White,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            screens.forEachIndexed { index, screenItem ->
                if (index == 2) (BottomNavigationItem(
                    selected = false,
                    onClick = { /*TODO*/ },
                    icon = { /*TODO*/ }))
                else {
                    BottomNavigationItem(
                        selected = currentDestination?.hierarchy?.any { screenItem.route == it.route } == true,
                        onClick = {
                            navController.navigate(screenItem.route) {
                                launchSingleTop = true
                            }
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = screenItem.iconId),
                                contentDescription = null
                            )
                        },
                        selectedContentColor = Color.Black,
                        unselectedContentColor = Gray,
                        modifier = Modifier.size(32.dp)
                    )
                }

            }
        }
    }
}