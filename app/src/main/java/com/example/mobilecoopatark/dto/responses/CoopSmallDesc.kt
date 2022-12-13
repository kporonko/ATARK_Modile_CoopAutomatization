package com.example.mobilecoopatark.dto.responses

@kotlinx.serialization.Serializable
data class CoopSmallDesc (
    var id: Int,
    var name: String,
    var eggsByWeek: Int,
    var thermometerIp: String,
    var thermometerApiKey: String
)