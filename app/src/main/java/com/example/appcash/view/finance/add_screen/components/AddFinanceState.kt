package com.example.appcash.view.finance.add_screen.components

import com.example.appcash.data.entities.Folder

data class AddFinanceState(
    val price: String = "",
    val query: String = "",
    val folders: List<Folder> = emptyList()
)
