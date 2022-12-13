package com.example.mobilecoopatark.ktor

object HttpRoutes {
    private const val BASE_URL = "http://10.0.2.2:5290"
    const val COOPS = "$BASE_URL/Coop/coops"
    const val LOGIN = "$BASE_URL/Profile/login"
    const val ADDFEEDING = "$BASE_URL/Coop/feeding"
    const val ADDCOLLECT = "$BASE_URL/Coop/egg-collect"

}