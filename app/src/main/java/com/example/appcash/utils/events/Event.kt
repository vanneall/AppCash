package com.example.appcash.utils.events

interface Event

data class SearchEvent(
    val searchQuery: String
): Event