package com.example.flowproject

import android.content.Context
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow

class MainViewModel(
    private val networkStateRepository: NetworkStateRepository,
    private val context: Context
) : ViewModel() {

    fun isNetworkAvailable() : Flow<Boolean> {
        return networkStateRepository.isInternetAvailable(context)
    }
}