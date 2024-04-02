package ru.point.domain.finance.interfaces

import ru.point.data.data.entities.Finance

interface InsertFinanceUseCase {
    operator fun invoke(value: Finance)
}