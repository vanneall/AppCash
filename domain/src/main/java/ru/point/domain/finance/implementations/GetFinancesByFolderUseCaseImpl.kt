package ru.point.domain.finance.implementations

import kotlinx.coroutines.flow.Flow
import ru.point.data.data.repository_interfaces.FinancesRepository
import ru.point.data.data.vo.FinanceCategorySubset
import ru.point.domain.finance.interfaces.GetFinancesByFolderUseCase
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters
import javax.inject.Inject

class GetFinancesByFolderUseCaseImpl @Inject constructor(
    private val repository: FinancesRepository
) : GetFinancesByFolderUseCase {
    override fun invoke(
        date: LocalDate,
    ): Flow<List<FinanceCategorySubset>> {
        val startMonth = date.with(TemporalAdjusters.firstDayOfMonth());
        val endMonth = date.with(TemporalAdjusters.lastDayOfMonth());
        return repository.getFinancesByFolderId(startMonth.toString(), endMonth.toString())
    }
}