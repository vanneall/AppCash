package com.example.appcash.view.finance.add_screen.components

import com.example.appcash.data.vo.IconFolderVO

data class AddFinanceState(
    val price: String = "",
    val query: String = "",
    val folders: List<IconFolderVO> = emptyList(),
    val isError: Boolean = false
)
