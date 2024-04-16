package ru.point.domain.finance.implementations

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.point.data.data.datasource.repository.interfaces.FinancesRepository
import ru.point.data.data.entity.subset.FinanceSubset
import ru.point.domain.finance.interfaces.GetFinancesByYearMonthUseCase
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters
import javax.inject.Inject

class GetFinancesByYearMonthUseCaseImpl @Inject constructor(
    private val repository: FinancesRepository
) : GetFinancesByYearMonthUseCase {

    private val todayDate = LocalDate.now()

    override fun invoke(date: LocalDate): Flow<List<FinanceSeparatorDto>> {
        val startMonth = date.with(TemporalAdjusters.firstDayOfMonth())
        val endMonth = date.with(TemporalAdjusters.lastDayOfMonth())

        val handledList = mutableListOf<FinanceSeparatorDto>()
        return repository.getFinancesByMonthId(startMonth.toString(), endMonth.toString())
            .map { list ->
                val todayOperation = list.takeWhileFrom(0) { day -> day.date == todayDate }

                val yesterdayOperation = list.takeWhileFrom(todayOperation.size) { day ->
                    day.date == todayDate.minusDays(1)
                }

                val otherDayOperation =
                    list.takeWhileFrom(todayOperation.size + yesterdayOperation.size) { true }


                handledList.addNotNull(
                    FinanceSeparatorDto(
                        FinanceViewSeparator.TODAY,
                        todayOperation
                    ).takeIf { it.list.isNotEmpty() },

                    FinanceSeparatorDto(
                        FinanceViewSeparator.YESTERDAY,
                        yesterdayOperation
                    ).takeIf { it.list.isNotEmpty() },

                    FinanceSeparatorDto(
                        FinanceViewSeparator.PREVIOUSLY,
                        otherDayOperation
                    ).takeIf { it.list.isNotEmpty() }
                )

                handledList
            }
    }

    fun <T> List<T>.takeWhileFrom(from: Int, predicate: (T) -> Boolean): List<T> {
        require(from >= 0) { "Количество элементов должно быть больше 0" }

        val mutableList = mutableListOf<T>()

        for (i in from until size) {
            if (predicate(get(i))) mutableList.add(get(i))
            else break
        }

        return mutableList
    }

    fun <T> MutableList<T>.addNotNull(vararg values: T?) {
        values.forEach { value -> if (value != null) add(value) }

    }
}

data class FinanceSeparatorDto(
    val separator: FinanceViewSeparator,
    val list: List<FinanceSubset>
)

enum class FinanceViewSeparator {
    TODAY,
    YESTERDAY,
    PREVIOUSLY,
}