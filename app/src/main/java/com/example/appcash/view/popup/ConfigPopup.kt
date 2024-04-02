package com.example.appcash.view.popup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appcash.view.ui.theme.DarkBlue
import com.example.appcash.view.ui.theme.DarkRed
import com.example.appcash.view.ui.theme.Orange

@Composable
fun ConfigPopup(
    name: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(40.dp)
    ) {
        Text(
            text = name,
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Удалить",
                tint = Color.White,
                modifier = Modifier
                    .size(size = 40.dp)
                    .background(color = DarkRed, shape = CircleShape)
                    .padding(5.dp)
            )

            Icon(
                imageVector = Icons.Default.Bookmark,
                contentDescription = "Добавить в закладки",
                tint = Color.White,
                modifier = Modifier
                    .size(size = 40.dp)
                    .background(color = Orange, shape = CircleShape)
                    .padding(5.dp)
            )

            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Редактировать",
                tint = Color.White,
                modifier = Modifier
                    .size(size = 40.dp)
                    .background(color = DarkBlue, shape = CircleShape)
                    .padding(5.dp)
            )
        }
        Spacer(modifier = Modifier.height(64.dp))
    }
}

@Preview
@Composable
fun ConfigPopupPreview() {
    ConfigPopup(
        name = "Название задачи 1",
        modifier = Modifier.size(width = 390.dp, height = 240.dp)
    )
}