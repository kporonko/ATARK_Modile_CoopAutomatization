package com.example.mobilecoopatark.dto.requests

import com.google.gson.annotations.SerializedName

data class LoginRequest (
    @SerializedName("login")
    var login: String,
    @SerializedName("password")
    var password: String
)