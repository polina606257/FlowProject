package com.example.flowproject

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(
    networkStateRepository: NetworkStateRepository,
    private val context: Context
) : ViewModel() {
    val isInternetAvailable = networkStateRepository.isInternetAvailable(context).shareIn(viewModelScope, SharingStarted.Lazily)
}