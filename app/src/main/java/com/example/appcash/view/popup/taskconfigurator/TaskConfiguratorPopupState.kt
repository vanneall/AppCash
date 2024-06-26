package com.example.appcash.view.popup.taskconfigurator

import java.time.LocalDate

data class TaskConfiguratorPopupState(
    val id: Long? = null,
    val isShowed: Boolean = false,
    val isDatePickerShowed: Boolean = false,
    val name: String = "",
    val isNameError: Boolean = false,
    val description: String = "",
    val parentId: Long? = null,
    val date: LocalDate? = null
)