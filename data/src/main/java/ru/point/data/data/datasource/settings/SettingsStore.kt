package ru.point.data.data.datasource.settings

import android.content.Context
import android.content.Context.MODE_PRIVATE
import javax.inject.Inject

class SettingsStore @Inject constructor(
    context: Context
) {
    private val preferences = context.getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE)

    var currency: String = preferences.getString(CURRENCY, "₽") ?: "₽"
        set(value) {
            preferences.edit().putString(CURRENCY, value).apply()
            field = value
        }


    private companion object {
        const val PREFERENCES_NAME = "PREFERENCES_NAME"
        const val CURRENCY = "CURRENCY"
    }

}