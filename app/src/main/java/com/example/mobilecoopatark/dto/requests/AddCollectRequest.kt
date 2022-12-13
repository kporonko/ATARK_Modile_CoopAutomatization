package com.example.mobilecoopatark.dto.requests

@kotlinx.serialization.Serializable
data class AddCollectRequest (
    var coopId: Int,
    var collectDate: String,
    var eggsCount: Int
)