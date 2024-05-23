package com.example.appcash.view.settings.main.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appcash.R
import com.example.appcash.navigation.Destinations
import com.example.appcash.view.FabState
import com.example.appcash.view.TopAppBarState
import com.example.appcash.view.ui.theme.DarkRed
import com.example.appcash.view.ui.theme.LightGray

@Composable
fun SettingsScreen(
    navigateTo: (String) -> Unit,
    navigateBack: () -> Unit,
    topAppBarState: MutableState<TopAppBarState>,
    fabState: MutableState<FabState>
) {
    topAppBarState.value = TopAppBarState(
        title = stringResource(id = R.string.settings_screen),
        navigationIcon = {
            IconButton(
                onClick = { navigateBack() },
                modifier = Modifier
                    .size(36.dp)
                    .background(color = LightGray, shape = CircleShape)
                    .padding(4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = null,
                    modifier = Modifier
                )
            }
        }
    )
    fabState.value = FabState { }

    Settings(
        navigateTo = navigateTo,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    )
}

@Composable
private fun Settings(
    navigateTo: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    val scrollableState = rememberScrollableState { 0f }

    Column(
        verticalArrangement = Arrangement.spacedBy(32.dp),
        modifier = modifier.scrollable(scrollableState, orientation = Orientation.Vertical)
    ) {
        SingleOption(
            text = stringResource(id = R.string.extension_version),
            onNavigate = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
        )

        SingleOption(
            text = stringResource(id = R.string.theme),
            onNavigate = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
        )

        SingleOption(
            text = stringResource(id = R.string.password),
            onNavigate = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
        )

        SingleOption(
            text = stringResource(id = R.string.currancy),
            onNavigate = { navigateTo(Destinations.SETTINGS_CURRENCY_SCREEN) },
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
        )

        SingleOption(
            text = stringResource(id = R.string.extension_version),
            onNavigate = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
        )

        SingleOption(
            text = stringResource(id = R.string.restore_purchase),
            onNavigate = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
        )

        SingleOption(
            text = stringResource(id = R.string.confidentiality),
            onNavigate = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
        )

        SingleOption(
            text = stringResource(id = R.string.delete_all_data),
            onNavigate = {},
            color = DarkRed,
            isArrowVisible = false,
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
        )
    }
}


@Composable
private fun SingleOption(
    text: String,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    color: Color = Color.Black,
    isArrowVisible: Boolean = true,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.clickable { onNavigate() }
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            color = color,
            fontWeight = FontWeight.Normal
        )

        if (isArrowVisible) {
            Icon(
                imageVector = Icons.Default.ArrowForwardIos,
                contentDescription = "Иконка продолжения",
                modifier = Modifier.size(size = 18.dp),
                tint = Color.Black
            )
        }
    }
}