package com.example.mobilecoopatark.dto.responses

@kotlinx.serialization.Serializable
data class TempResponse (
    val feeds: List<TempResponseItem>,
    val channel: ChannelResponse
)

@kotlinx.serialization.Serializable
data class TempResponseItem(
    val created_at: String,
    val entry_id: Int,
    val field1: String
)

@kotlinx.serialization.Serializable
data class ChannelResponse(
    val id: Int,
    val name: String,
    val description: String,
    val latitude: String,
    val longitude: String,
    val field1: String,
    val created_at: String,
    val updated_at: String,
    val last_entry_id: Int
)