package com.example.flowproject

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val networkStateRepository: NetworkStateRepository,
    private val context: Context
) : ViewModel() {
    private val _isInternetAvailable = MutableStateFlow(NetworkState(false))
    val isInternetAvailable = _isInternetAvailable.asStateFlow()

    fun getIsNetworkAvailable() {
        viewModelScope.launch {
            networkStateRepository.isInternetAvailable(context).collect { isInternetConnected ->
                _isInternetAvailable.value = NetworkState(isInternetConnected)
            }
        }
    }
}