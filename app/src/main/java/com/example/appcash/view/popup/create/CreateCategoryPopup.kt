package com.example.appcash.view.popup.create

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appcash.R
import com.example.appcash.utils.ParamsStore.colorsList
import com.example.appcash.utils.ParamsStore.iconsList
import com.example.appcash.utils.events.Event
import com.example.appcash.view.ui.theme.DarkBlue
import com.example.appcash.view.ui.theme.LightGray

@Composable
fun CreateCategoryPopup(
    state: CreateCategoryPopupState,
    onEvent: (Event) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = stringResource(id = R.string.create_folder),
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )

        TextField(
            value = state.name,
            onValueChange = { newName ->
                onEvent(CreateCategoryPopupEvent.InputName(newName))
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(fontSize = 14.sp),
            isError = state.isNameTextFieldError,
            label = { Text(text = stringResource(id = R.string.name)) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(15.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                unfocusedContainerColor = LightGray,
                focusedContainerColor = LightGray
            ),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                imeAction = ImeAction.Done
            )
        )

        val icons = iconsList

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            itemsIndexed(
                items = icons
            ) { index, icon ->
                Icon(
                    painter = icon,
                    contentDescription = null,
                    modifier = Modifier
                        .size(52.dp)
                        .background(color = LightGray, shape = CircleShape)
                        .border(
                            width = 1.dp,
                            shape = CircleShape,
                            color = if (index == state.selectedIconIndex) Color.Black else Color.Transparent
                        )
                        .clickable {
                            onEvent(CreateCategoryPopupEvent.SelectIcon(index))
                        }
                        .padding(5.dp)
                )
            }
        }

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            itemsIndexed(
                items = colorsList
            ) { index, color ->
                Box(modifier = Modifier
                    .size(52.dp)
                    .background(color = color, shape = CircleShape)
                    .clickable {
                        onEvent(
                            CreateCategoryPopupEvent.SelectColor(
                                color.toArgb(),
                                index
                            )
                        )
                    }
                    .border(
                        width = 1.dp,
                        shape = CircleShape,
                        color = if (index == state.selectedColorIndex) Color.Black else Color.Transparent
                    )
                )
            }
        }

        Button(
            onClick = { onEvent(CreateCategoryPopupEvent.CreateCategory) },
            colors = ButtonDefaults.buttonColors(
                containerColor = DarkBlue,
                contentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.create),
                fontSize = 16.sp,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}