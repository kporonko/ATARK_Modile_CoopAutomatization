//package com.example.mobilecoopatark.viewmodels
//
//import android.app.Application
//import androidx.lifecycle.AndroidViewModel
//import androidx.lifecycle.MutableLiveData
//import com.example.mobilecoopatark.auth.BaseResponse
//import com.example.mobilecoopatark.dto.responses.CoopSmallDesc
//import com.example.mobilecoopatark.repos.UserRepository
//
//class CoopsViewModel(application: Application) : AndroidViewModel(application) {
//    private val userRepo = UserRepository()
//    val result: MutableLiveData<BaseResponse<List<CoopSmallDesc>>> = MutableLiveData()
//
//    fun getCoops(profileId: Int) {
////        result.value = BaseResponse.Loading()
////        viewModelScope.launch {
////            try {
////                val response = userRepo.getCoops(profileId = profileId)
////                if (response?.code() == 200) {
////                    result.value = BaseResponse.Success(response.body())
////                } else {
////                    result.value = BaseResponse.Error(response?.message())
////                }
////
////            } catch (ex: Exception) {
////                result.value = BaseResponse.Error(ex.message)
////            }
////        }
//    }
//}