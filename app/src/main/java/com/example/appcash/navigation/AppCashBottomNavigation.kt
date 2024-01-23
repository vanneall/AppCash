package com.example.appcash.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun AppCashBottomNavigation(
    navController: NavController,
    screens: List<Screen>,
    modifier: Modifier = Modifier
) {
    BottomNavigation(
        modifier = modifier,
        backgroundColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        screens.forEach { screenItem ->
            BottomNavigationItem(
                selected = screenItem.route == currentDestination?.route,
                onClick = {
                    navController.navigate(screenItem.route)
                },
                icon = {
                    Icon(
                        painter = painterResource(id = screenItem.iconId),
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = screenItem.name
                    )
                }
            )
        }
    }
}