package com.example.appcash.utils.mode

interface OpenModeHandler {

    val DEFAULT_VALUE_STRING: String
    fun handle(mode: String): OpenMode
}