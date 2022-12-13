//package com.example.mobilecoopatark.repos
//
//import com.example.mobilecoopatark.auth.UserApi
//import com.example.mobilecoopatark.dto.responses.CoopSmallDesc
//import com.example.mobilecoopatark.dto.responses.LoginResponse
//import retrofit2.Response
//
//class UserRepository {
//    suspend fun loginUser(login: String, password: String): Response<LoginResponse>? {
//        return UserApi.getApi()?.loginUser(login = login, password = password)
//    }
//
//    suspend fun getCoops(profileId: Int): Response<List<CoopSmallDesc>>? {
//        return UserApi.getApi()?.getCoops(profileId = profileId)
//    }
//}