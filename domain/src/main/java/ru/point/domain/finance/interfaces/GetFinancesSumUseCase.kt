package ru.point.domain.finance.interfaces

import kotlinx.coroutines.flow.Flow

interface GetFinancesSumUseCase {
    fun invoke(): Flow<Int?>
}