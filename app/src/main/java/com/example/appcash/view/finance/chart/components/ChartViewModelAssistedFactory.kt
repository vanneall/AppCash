package com.example.appcash.view.finance.chart.components

import com.example.appcash.utils.ArgsKeys
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent


@AssistedFactory
interface ChartViewModelAssistedFactory {
    fun create(
        @Assisted(ArgsKeys.OPEN_MODE_KEY) openMode: OpenMode,
    ): ChartScreenViewModel
}

@EntryPoint
@InstallIn(ActivityComponent::class)
interface ChartViewModelFactoryProvider {
    fun provideChartViewModelAssistedFactory(): ChartViewModelAssistedFactory
}