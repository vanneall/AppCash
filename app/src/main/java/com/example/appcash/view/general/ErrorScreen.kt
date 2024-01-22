package com.example.appcash.view.general

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun ErrorScreen(message: String) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "ERROR: $message",
            color = Color.Red,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )
    }
}