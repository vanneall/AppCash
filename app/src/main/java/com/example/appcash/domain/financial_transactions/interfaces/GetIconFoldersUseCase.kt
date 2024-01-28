package com.example.appcash.domain.financial_transactions.interfaces

import com.example.appcash.data.vo.IconFolderVO
import com.example.appcash.utils.events.Event
import kotlinx.coroutines.flow.Flow

interface GetIconFoldersUseCase {
    fun invoke(onError: (Event.ErrorEvent) -> Unit): Flow<List<IconFolderVO>>
}