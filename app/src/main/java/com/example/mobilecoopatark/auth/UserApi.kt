//package com.example.mobilecoopatark.auth
//
//import com.example.mobilecoopatark.dto.responses.CoopSmallDesc
//import com.example.mobilecoopatark.dto.responses.LoginResponse
//import retrofit2.Response
//import retrofit2.http.GET
//import retrofit2.http.Path
//import retrofit2.http.Query
//
//interface UserApi {
//    @GET("Profile/login")
//    suspend fun loginUser(@Query("login") login: String, @Query("password") password: String): Response<LoginResponse>
//
//    @GET("Coop/coops/{profileId}}")
//    suspend fun getCoops(@Path("profileId") profileId : Int): Response<List<CoopSmallDesc>>
//
//    companion object {
//        fun getApi(): UserApi? {
//            return ApiClient.client?.create(UserApi::class.java)
//        }
//    }
//}