package com.example.appcash.utils.mode

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.appcash.R
import ru.point.domain.finance.implementations.FinanceViewSeparator

@Composable
fun convertToString(mode: FinanceViewSeparator): String {
    return when (mode) {
        FinanceViewSeparator.TODAY -> stringResource(id = R.string.today)
        FinanceViewSeparator.PREVIOUSLY -> stringResource(id = R.string.previosly_in_month)
        FinanceViewSeparator.YESTERDAY -> stringResource(id = R.string.yesterday)
    }
}