package com.example.appcash.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
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
            val navController = rememberNavController()
            AppCashTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        bottomBar = {
                            AppCashBottomNavigation(
                                navController = navController,
                                screens = screenBottomItems,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    ) {it
                        AppCashNavHost(navController)
                    }
                }
            }
        }
    }
}