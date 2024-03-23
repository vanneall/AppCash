package com.example.appcash.domain.financial_transactions.implementations

import com.example.appcash.data.entities.Category
import com.example.appcash.data.entities.Finance
import com.example.appcash.data.repository_interfaces.FinancesRepository
import com.example.appcash.domain.financial_transactions.interfaces.GetFinancesByYearMonthUseCase
import kotlinx.coroutines.flow.Flow
import java.time.YearMonth
import javax.inject.Inject

class GetFinancesByYearMonthUseCaseImpl @Inject constructor(
    private val repository: FinancesRepository
) : GetFinancesByYearMonthUseCase {
    override fun invoke(yearMonth: YearMonth): Flow<Map<Finance, Category>> {
        val monthId = yearMonth.toString()
        return repository.getFinancesByMonthId(monthId)
    }
}