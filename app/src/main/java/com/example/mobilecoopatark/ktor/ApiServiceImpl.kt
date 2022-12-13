package com.example.mobilecoopatark.ktor

import com.example.mobilecoopatark.dto.requests.AddCollectRequest
import com.example.mobilecoopatark.dto.requests.AddFeedingRequest
import com.example.mobilecoopatark.dto.responses.CoopSmallDesc
import com.example.mobilecoopatark.dto.responses.LoginResponse
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*

class ApiServiceImpl(
    private val client: HttpClient
) : ApiService {

    override suspend fun getCoops(profileId: Int): List<CoopSmallDesc> {
        return try {
            client.get { url(HttpRoutes.COOPS + "/" + profileId) }
        } catch(e: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch(e: ClientRequestException) {
            // 4xx - responses
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch(e: ServerResponseException) {
            // 5xx - responses
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch(e: Exception) {
            println("Error: ${e.message}")
            emptyList()
        }
    }

    override suspend fun login(email: String, password: String): LoginResponse {
        return try {
            client.get{url(HttpRoutes.LOGIN+ "?login=" + email + "&password=" + password)}
        }catch(e: Exception) {
            println("Error: ${e.message}")
            LoginResponse(email="", profileId = -1, firstFeeding = "00:00", secondFeeding = "00:00")
        }
    }

    override suspend fun addFeeding(feedingRequest: AddFeedingRequest): HttpStatusCode {
        return try {
            client.post<HttpStatusCode> {
                url(HttpRoutes.ADDFEEDING)
                contentType(ContentType.Application.Json)
                body = feedingRequest
            }
        }
        catch(e: Exception) {
            HttpStatusCode.BadRequest
        }
    }

    override suspend fun addCollect(collectRequest: AddCollectRequest): HttpStatusCode {
        return try {
            client.post<HttpStatusCode> {
                url(HttpRoutes.ADDCOLLECT)
                contentType(ContentType.Application.Json)
                body = collectRequest
            }
        }
        catch(e: Exception) {
            HttpStatusCode.BadRequest
        }    }

}