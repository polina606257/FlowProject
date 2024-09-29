package com.example.flowproject

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NetworkStateRepository {
    private val isInternetConnected = MutableStateFlow(false)

    fun isInternetAvailable(context: Context): StateFlow<Boolean>  {

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()

        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                isInternetConnected.value = true
            }

            override fun onLost(network: Network) {
                isInternetConnected.value = false
            }
        }
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
        return isInternetConnected
    }
}
