package com.example.mobilecoopatark.dto.responses

import com.google.gson.annotations.SerializedName

@kotlinx.serialization.Serializable
data class LoginResponse (
      @SerializedName("profileId")
      var profileId: Int,
      @SerializedName("firstFeeding")
      var firstFeeding: String,
      @SerializedName("secondFeeding")
      var secondFeeding: String,
      @SerializedName("email")
      var email: String
)