package com.example.appcash.utils.mode

interface OpenModeHandler {
    fun handle(mode: String): OpenMode
}