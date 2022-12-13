package com.example.mobilecoopatark.dto.requests


@kotlinx.serialization.Serializable
data class AddFeedingRequest (
    var coopId: Int,
    var feedingDate: String
)