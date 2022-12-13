package com.example.mobilecoopatark

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.mobilecoopatark.components.Start
import com.example.mobilecoopatark.ktor.ApiService
import com.example.mobilecoopatark.viewmodels.ModalCollectViewModel
import com.example.mobilecoopatark.viewmodels.ModalViewModel


class MainActivity : ComponentActivity() {

    private val service = ApiService.create()

    private val viewModel by viewModels<ModalViewModel>()
    private val viewModelCollect by viewModels<ModalCollectViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            Start(activity = this, viewModel = viewModel, viewModelCollect = viewModelCollect)
        }
    }
}
