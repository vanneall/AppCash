@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.appcash.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FabPosition
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
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
            val fabActionState = remember { mutableStateOf(FabState({})) }
            val navController = rememberNavController()

            AppCashTheme(
                dynamicColor = false
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        topBar = {
                            AppCashTopAppBar(
                                state = topAppBarState.value,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 20.dp, start = 16.dp)
                            )
                        },
                        bottomBar = {
                            AppCashBottomNavigation(
                                navController = navController,
                                screens = screenBottomItems,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(80.dp)
                            )
                        },
                        floatingActionButton = {
                            FloatingActionButton(
                                onClick = fabActionState.value.action,
                                shape = CircleShape,
                                elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 0.dp),
                                containerColor = Color.Black,
                                contentColor = Color.White,
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = null,
                                    tint = Color.White
                                )
                            }
                        },
                        isFloatingActionButtonDocked = true,
                        floatingActionButtonPosition = FabPosition.Center

                    ) { paddingValues ->
                        AppCashNavHost(
                            navHostController = navController,
                            topAppBarState = topAppBarState,
                            fabState = fabActionState,
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
    val title: String = "",
    val navigationIcon: (@Composable () -> Unit)? = null,
    val actions: (@Composable RowScope.() -> Unit)? = null
)

data class FabState(
    val action: () -> Unit,
)

@Composable
private fun AppCashTopAppBar(
    state: TopAppBarState,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = state.title,
                color = Color.Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold
            )
        },
        navigationIcon = state.navigationIcon ?: {},
        actions = state.actions ?: {},
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.White),
        modifier = modifier
    )
}