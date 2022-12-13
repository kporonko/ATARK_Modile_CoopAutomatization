//package com.example.mobilecoopatark.viewmodels
//
//import android.app.Application
//import androidx.lifecycle.AndroidViewModel
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.viewModelScope
//import com.example.mobilecoopatark.auth.BaseResponse
//import com.example.mobilecoopatark.dto.responses.LoginResponse
//import com.example.mobilecoopatark.repos.UserRepository
//import kotlinx.coroutines.launch
//
//class LoginViewModel(application: Application) : AndroidViewModel(application) {
//    private val userRepo = UserRepository()
//    val loginResult: MutableLiveData<BaseResponse<LoginResponse>> = MutableLiveData()
//    fun loginUser(login: String, password: String) {
//
//        loginResult.value = BaseResponse.Loading()
//        viewModelScope.launch {
//            try {
//                val response = userRepo.loginUser(login = login, password=password)
//                if (response?.code() == 200) {
//                    loginResult.value = BaseResponse.Success(response.body())
//                } else {
//                    loginResult.value = BaseResponse.Error(response?.message())
//                }
//
//            } catch (ex: Exception) {
//                loginResult.value = BaseResponse.Error(ex.message)
//            }
//        }
//    }
//}