package com.example.appcash.view.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appcash.R
import com.example.appcash.view.TopAppBarState
import com.example.appcash.view.ui.theme.DarkBlue
import com.example.appcash.view.ui.theme.Gray
import com.example.appcash.view.ui.theme.LightGray
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.daysOfWeek
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun CalendarScreen(
    topAppBarState: MutableState<TopAppBarState>
) {
    topAppBarState.value = TopAppBarState(
        title = stringResource(id = R.string.calendar_screen)
    )

    MainScreen()
}

@Composable
fun MainScreen() {
    val currentMonth = remember { YearMonth.now() }
    val startMonth = remember { currentMonth.minusMonths(60) }
    val endMonth = remember { currentMonth.plusMonths(60) }
    val daysOfWeek = remember { daysOfWeek() }
    val coroutineScope = rememberCoroutineScope()

    val state = rememberCalendarState(
        startMonth = startMonth,
        endMonth = endMonth,
        firstVisibleMonth = currentMonth,
        firstDayOfWeek = daysOfWeek.first()
    )

    Column {
        MonthHeader(
            month = state.lastVisibleMonth.yearMonth,
            onBackButton = {
                coroutineScope.launch {
                    state.animateScrollToMonth(
                        state.lastVisibleMonth.yearMonth.minusMonths(1)
                    )
                }
            },
            onForwardButton = {
                coroutineScope.launch {
                    state.animateScrollToMonth(
                        state.lastVisibleMonth.yearMonth.plusMonths(1)
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        DaysOfWeekTitle(
            daysOfWeek = daysOfWeek
        )

        Spacer(modifier = Modifier.height(20.dp))

        HorizontalCalendar(
            state = state,
            dayContent = { Day(it) },
        )
    }
}


@Composable
fun MonthHeader(
    month: YearMonth,
    onBackButton: () -> Unit,
    onForwardButton: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBackIosNew,
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
                .clickable { onBackButton() }
                .background(color = LightGray, shape = CircleShape)
                .padding(6.dp)
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = mothHeaderFormatter(month),
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            modifier = Modifier.width(164.dp)
        )

        Spacer(modifier = Modifier.width(4.dp))

        Icon(
            imageVector = Icons.Default.ArrowForwardIos,
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
                .clickable { onForwardButton() }
                .background(color = LightGray, shape = CircleShape)
                .padding(6.dp)
        )
    }
}

private fun mothHeaderFormatter(month: YearMonth): String {
    val monthString = month.month
        .getDisplayName(
            TextStyle.FULL_STANDALONE,
            Locale.getDefault()
        )
        .replaceFirstChar { char -> char.uppercaseChar() }
    return "$monthString ${month.year}"
}

@Composable
fun Day(day: CalendarDay) {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(10.dp)
            .background(
                color = if (day.date == LocalDate.now()) DarkBlue else Color.Transparent,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = day.date.dayOfMonth.toString(),
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = when {
                day.date == LocalDate.now() -> Color.White
                day.position == DayPosition.MonthDate -> Color.Black
                else -> Gray
            }
        )
    }
}

@Composable
fun DaysOfWeekTitle(
    daysOfWeek: List<DayOfWeek>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        ) {
            for (dayOfWeek in daysOfWeek) {
                Text(
                    textAlign = TextAlign.Center,
                    text = dayOfWeek
                        .getDisplayName(TextStyle.SHORT, Locale.getDefault())
                        .replaceFirstChar { char -> char.uppercaseChar() },
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    modifier = Modifier.weight(1f),
                )
            }
        }
        Divider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = LightGray
        )
    }
}