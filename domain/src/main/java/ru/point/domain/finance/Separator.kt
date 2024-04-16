package ru.point.domain.finance

import ru.point.data.data.entity.subset.FinanceSubset
import ru.point.domain.finance.implementations.FinanceSeparatorDto
import ru.point.domain.finance.implementations.FinanceViewSeparator
import ru.point.domain.finance.implementations.addNotNull
import ru.point.domain.finance.implementations.takeWhileFrom
import java.time.LocalDate

fun separate(
    todayDate: LocalDate = LocalDate.now(),
    list: List<FinanceSubset>
): List<FinanceSeparatorDto> {
    val handledList = mutableListOf<FinanceSeparatorDto>()
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

    return handledList
}