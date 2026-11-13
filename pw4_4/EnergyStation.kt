package com.example.pw4_4.data
data class EnergyStation(
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val type: String = "",
    val description: String = "",
    val power: Int = 0,
    val status: String = ""
)