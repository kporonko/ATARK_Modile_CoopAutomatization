package com.example.mobilecoopatark.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ModalViewModel: ViewModel() {

    var isDialogShown by mutableStateOf(false)
        private set

    fun onPurchaseClick(){
        isDialogShown = true
    }

    fun onDismissDialog(){
        isDialogShown = false
    }
}