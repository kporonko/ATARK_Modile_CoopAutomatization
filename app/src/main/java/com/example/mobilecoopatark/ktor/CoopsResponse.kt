package com.example.mobilecoopatark.ktor

@kotlinx.serialization.Serializable
data class CoopsResponse(
    val id: Int,
    val name: String,
    val eggsByWeek: Int,
    val thermometerIp: String,
    val thermometerApiKey: String
)
