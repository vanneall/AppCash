@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.appcash.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.appcash.navigation.AppCashBottomNavigation
import com.example.appcash.navigation.AppCashNavHost
import com.example.appcash.navigation.screenBottomItems
import com.example.appcash.view.ui.theme.AppCashTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val topAppBarState = remember { mutableStateOf(TopAppBarState()) }
            val navController = rememberNavController()
            AppCashTheme(dynamicColor = false) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        topBar = {
                            AppCashTopAppBar(
                                state = topAppBarState.value
                            )
                        },
                        bottomBar = {
                            AppCashBottomNavigation(
                                navController = navController,
                                screens = screenBottomItems,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    ) { paddingValues ->
                        AppCashNavHost(
                            navHostController = navController,
                            topAppBarState = topAppBarState,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(paddingValues = paddingValues)
                        )
                    }
                }
            }
        }
    }
}

data class TopAppBarState(
    val title: String = "Задачник",
    val navigationIcon: (@Composable () -> Unit)? = null,
    val actions: (@Composable RowScope.() -> Unit)? = null
)

@Composable
private fun AppCashTopAppBar(
    state: TopAppBarState
) {
    TopAppBar(
        title = {
            Text(
                text = state.title,
                color = Color.Black,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = state.navigationIcon ?: {},
        actions = state.actions ?: {}
    )
}