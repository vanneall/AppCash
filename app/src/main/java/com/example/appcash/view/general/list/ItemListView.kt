package com.example.appcash.view.general.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ItemListView(
    name: String,
    icon: Painter,
    iconColor: Color = Color.White,
    backgroundIconColor: Color,
    onClick: () -> Unit,
    iconSize: Dp = 28.dp,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        RoundedIconView(
            icon = icon,
            tint = iconColor,
            backGroundColor = backgroundIconColor,
            size = iconSize
        )
        Text(
            text = name,
            fontSize = 17.sp,
            modifier = Modifier.padding(start = 10.dp)
        )
    }
}