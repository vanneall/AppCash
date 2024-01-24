package com.example.appcash.utils.mode

interface OpenModeHandler {

    val ERROR_VALUE_STRING: String
    fun handle(mode: String): OpenMode
}