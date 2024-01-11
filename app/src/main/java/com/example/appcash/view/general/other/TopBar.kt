package com.example.appcash.view.general.other

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.example.appcash.R
import com.example.appcash.view.ui.theme.TopBarDateColor

private const val MENU_ICON_ID = "menu_icon_id"
private const val DATE_TEXT_ID = "date_text_id"
private const val CALENDAR_SEARCH_ICON_ID = "calendar_search_icon_id"
private const val CALENDAR_ICON_ID = "calendar_icon_id"

private val constraints = ConstraintSet {
    val menuIcon = createRefFor(MENU_ICON_ID)
    val dateText = createRefFor(DATE_TEXT_ID)
    val calendarSearchIcon = createRefFor(CALENDAR_SEARCH_ICON_ID)
    val calendarIcon = createRefFor(CALENDAR_ICON_ID)

    constrain(menuIcon) {
        start.linkTo(parent.start)
        top.linkTo(parent.top)
        centerVerticallyTo(parent)
    }

    constrain(dateText) {
        start.linkTo(menuIcon.end)
        top.linkTo(parent.top)
        centerVerticallyTo(parent)

    }

    constrain(calendarSearchIcon) {
        end.linkTo(calendarIcon.start)
        top.linkTo(parent.top)
        centerVerticallyTo(parent)
    }

    constrain(calendarIcon) {
        end.linkTo(parent.end)
        top.linkTo(parent.top)
        centerVerticallyTo(parent)
    }
}

@Composable
fun TopBar() {
    ConstraintLayout(
        constraintSet = constraints,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Unspecified)
            .padding(vertical = 10.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = null,
            modifier = Modifier
                .size(26.dp)
                .layoutId(MENU_ICON_ID)
        )
        Text(
            text = "23 Сентября",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = TopBarDateColor,
            modifier = Modifier
                .padding(start = 14.dp)
                .layoutId(DATE_TEXT_ID)
        )
        Icon(
            painter = painterResource(id = R.drawable.calendar_search_icon),
            contentDescription = null,
            modifier = Modifier
                .padding(5.dp)
                .layoutId(CALENDAR_SEARCH_ICON_ID)
        )
        Icon(
            painter = painterResource(id = R.drawable.calendar_icon),
            contentDescription = null,
            modifier = Modifier
                .padding(5.dp)
                .layoutId(CALENDAR_ICON_ID)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewTopBar() {
    TopBar()
}