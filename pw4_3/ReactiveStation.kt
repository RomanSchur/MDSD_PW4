package com.example.pw4_3.data
import java.time.LocalDate
data class ReactiveStation(
    val name: String,
    val type: String,
    val power: Double,
    val lastUpdate: LocalDate,
    val status: String
)