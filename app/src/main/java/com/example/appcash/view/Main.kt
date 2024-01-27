package com.example.appcash.view

import com.example.appcash.utils.ParamsStore
import com.example.appcash.utils.ParamsStore.getSafety

fun main(){
    println(ParamsStore.colorsList.size)
    println(ParamsStore.colorsList.getSafety(-1))
    println(ParamsStore.colorsList.getSafety(0))
    println(ParamsStore.colorsList.getSafety(8))
    println(ParamsStore.colorsList.getSafety(9))
    println(ParamsStore.colorsList.getSafety(10))
}