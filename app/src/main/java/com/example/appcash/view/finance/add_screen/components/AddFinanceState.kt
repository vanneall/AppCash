package com.example.appcash.view.finance.add_screen.components

import com.example.appcash.data.dto.FolderDto

data class AddFinanceState(
    val price: String = "",
    val query: String = "",
    val folders: List<FolderDto> = emptyList()
)
