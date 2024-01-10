package com.example.appcash.view.general.other

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.appcash.R
import com.example.appcash.utils.StringExtensions
import com.example.appcash.utils.events.Event
import com.example.appcash.utils.events.SearchEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTextField(
    onEvent: (Event) -> Unit,
) {
    val searchQuery = remember { mutableStateOf(StringExtensions.EMPTY_STRING) }

    val isDeleteIconEnabled = remember {
        derivedStateOf { searchQuery.value.isNotEmpty() }
    }
    OutlinedTextField(
        value = searchQuery.value,
        onValueChange = { newValue ->
            searchQuery.value = newValue
            onEvent(
                SearchEvent(
                    searchQuery = searchQuery.value
                )
            )
        },
        singleLine = true,
        label = { Text(text = stringResource(id = R.string.search)) },
        shape = RoundedCornerShape(corner = CornerSize(14.dp)),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        trailingIcon = {
            if (isDeleteIconEnabled.value) Icon(
                imageVector = Icons.Default.Close,
                contentDescription = null,
                modifier = Modifier.clickable {
                    searchQuery.value = StringExtensions.EMPTY_STRING
                    onEvent(
                        SearchEvent(
                            searchQuery = searchQuery.value
                        )
                    )
                }
            )
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = {
            onEvent(
                SearchEvent(
                    searchQuery = searchQuery.value
                )
            )
        }),
        modifier = Modifier.fillMaxWidth()
    )
}