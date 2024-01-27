package com.example.appcash.utils.events

interface Event {
    object ErrorEvent: Event
}

data class SearchEvent(
    val query: String
): Event