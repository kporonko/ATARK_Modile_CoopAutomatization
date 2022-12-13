package com.example.mobilecoopatark.ktor

import com.example.mobilecoopatark.dto.requests.AddCollectRequest
import com.example.mobilecoopatark.dto.requests.AddFeedingRequest
import com.example.mobilecoopatark.dto.responses.CoopSmallDesc
import com.example.mobilecoopatark.dto.responses.LoginResponse
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.http.*

interface ApiService {

    suspend fun getCoops(profileId: Int): List<CoopSmallDesc>
    suspend fun login(email: String, password: String): LoginResponse
    suspend fun addFeeding(feedingRequest: AddFeedingRequest): HttpStatusCode
    suspend fun addCollect(feedingRequest: AddCollectRequest): HttpStatusCode

    companion object {
        fun create(): ApiService {
            return ApiServiceImpl(
                client = HttpClient(Android) {
                    install(Logging) {
                        level = LogLevel.ALL
                    }
                    install(JsonFeature) {
                        serializer = KotlinxSerializer()
                    }
                }
            )
        }
    }
}