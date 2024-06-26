package com.example.appcash.view.general.list

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun Header(
    name: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = name,
        fontSize = 24.sp,
        textAlign = TextAlign.Start,
        fontWeight = FontWeight.Medium,
        modifier = modifier
    )
}