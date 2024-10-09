package com.example.flowproject

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val networkStateRepository: NetworkStateRepository,
    private val context: Context
) : ViewModel() {

    private val _isInternetConnected = MutableSharedFlow<NetworkState>()
    val isInternetConnected = _isInternetConnected

    init {
        viewModelScope.launch {
            networkStateRepository.isInternetAvailable(context, viewModelScope).collect { networkState ->
                _isInternetConnected.emit(NetworkState(networkState.isConnected))
            }
        }
    }
}