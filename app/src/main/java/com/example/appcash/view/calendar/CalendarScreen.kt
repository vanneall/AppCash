package com.example.appcash.view.calendar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.example.appcash.view.TopAppBarState

@Composable
fun CalendarScreen(
    topAppBarState: MutableState<TopAppBarState>
) {
    topAppBarState.value = TopAppBarState(
        title = "Календарь"
    )

    AppCacheCalendar()
}