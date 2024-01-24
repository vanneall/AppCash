package com.example.appcash.utils.events

interface Event {
    data class ErrorEvent(
        val message: String
    ): Event
}

data class SearchEvent(
    val query: String
): Event