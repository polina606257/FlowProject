package com.example.flowproject

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope

class MainViewModelFactory(
    private val networkStateRepository: NetworkStateRepository,
    private val context: Context
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(networkStateRepository, context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}