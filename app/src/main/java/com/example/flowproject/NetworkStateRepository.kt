package com.example.flowproject

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOf

class NetworkStateRepository {

    fun isInternetAvailable(context: Context): Flow<NetworkState> = callbackFlow {

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()

        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                trySend(NetworkState(true))
            }

            override fun onLost(network: Network) {
                trySend(NetworkState(false))
            }
        }
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
        awaitClose {
            connectivityManager.unregisterNetworkCallback(networkCallback)
        }
    }
}
