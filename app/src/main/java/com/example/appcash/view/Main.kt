package com.example.appcash.view

import com.example.appcash.utils.ParamsStore.colorsList
import com.example.appcash.utils.ParamsStore.getRandomColorIndex

fun main(){
    var a = false
    while (!a) {
        a = 0 == colorsList.getRandomColorIndex()
        println("нет")
    }
}