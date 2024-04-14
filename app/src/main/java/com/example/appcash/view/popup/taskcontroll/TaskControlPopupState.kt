package com.example.appcash.view.popup.taskcontroll

import java.time.LocalDate

data class TaskControlPopupState(
    val isShowed: Boolean = false,
    val id: Long = 0,
    val name: String = "",
    val description: String? = null,
    val localDate: LocalDate? = null
)
