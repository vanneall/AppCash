package com.example.appcash.domain.financial_transactions.implementations

import android.util.Log
import com.example.appcash.data.repository_interfaces.FinancialTransactionsRepository
import com.example.appcash.data.vo.IconFolderVO
import com.example.appcash.domain.financial_transactions.interfaces.GetIconFoldersUseCase
import com.example.appcash.utils.events.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

class GetIconFoldersUseCaseImpl @Inject constructor(
    private val repository: FinancialTransactionsRepository
) : GetIconFoldersUseCase {
    override fun invoke(onError: (Event.ErrorEvent) -> Unit): Flow<List<IconFolderVO>> {
        return try {
            repository.getIconFolders()
        } catch (ex: Exception) {
            onError(Event.ErrorEvent)
            Log.e("Selection IconFolderVo exception", ex.stackTraceToString())
            emptyFlow()
        }
    }
}