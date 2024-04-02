@file:OptIn(
    ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package com.example.appcash.view.general.other


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appcash.utils.ParamsStore.colorsList
import com.example.appcash.utils.events.Event
import com.example.appcash.view.notes.notefolders.components.MainNotesEvent.InsertFolder

@Composable
fun FolderSettingsModalBottomSheet(
    sheetState: SheetState,
    onEvent: (Event) -> Unit,
    modifier: Modifier = Modifier
) {
    //TODO переписать хранение состояния в state
    val name = remember { mutableStateOf("") }
    val selectedColorIndex = remember { mutableIntStateOf(0) }

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = { onEvent(BottomSheetEvent.HideEvent) },
        modifier = modifier,
    ) {
        OutlinedTextField(
            value = name.value,
            onValueChange = { name.value = it },
            label = { Text(text = "Название папки") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                capitalization = KeyboardCapitalization.Sentences
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )
        Spacer(modifier = Modifier.height(5.dp))
        LazyRow(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 15.dp),
            modifier = modifier.fillMaxWidth(),
        ) {
            itemsIndexed(colorsList) { index, color ->
                CircleColor(
                    color = color,
                    isSelected = index == selectedColorIndex.intValue,
                    modifier = Modifier.clickable { selectedColorIndex.intValue = index }
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, bottom = 40.dp)
        ) {
            Button(
                onClick = { onEvent(BottomSheetEvent.HideEvent) },
                modifier = Modifier.weight(0.5f)
            ) {
                Text(
                    text = "Отменить",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
            Button(
                onClick = {
                    onEvent(
                        InsertFolder(
                            name = name.value,
                            color = selectedColorIndex.intValue
                        )
                    )
                    onEvent(BottomSheetEvent.HideEvent)
                },
                modifier = Modifier.weight(0.5f)
            ) {
                Text(
                    text = "Сохранить",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }
    }
}

sealed class BottomSheetEvent : Event {
    object ShowEvent : Event
    object HideEvent : Event
}

@Composable
private fun CircleColor(
    color: Color,
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(50.dp)
            .background(color = color, shape = CircleShape)
            .border(
                border = BorderStroke(
                    width = 1.dp,
                    color = if (isSelected) Color.Black else Color.Transparent
                ),
                shape = CircleShape
            )
    )
}