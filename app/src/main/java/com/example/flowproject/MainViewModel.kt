package com.example.flowproject

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(
    private val networkStateRepository: NetworkStateRepository,
    private val context: Context
) : ViewModel() {

    private val _isInternetConnected = MutableStateFlow(false)
    val isInternetConnected = _isInternetConnected

    init {
        viewModelScope.launch {
            getInternetState().collect { networkState ->
                _isInternetConnected.value = networkState.isConnected
            }
        }
    }

    private fun getInternetState() : Flow<NetworkState> {
        return networkStateRepository.isInternetAvailable(context)
    }
}