package com.example.flowproject

import android.view.View
import android.view.View.OnClickListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

fun View.clicksFlow(): Flow<Unit> = callbackFlow {
    val listener = object : OnClickListener {
        override fun onClick(p0: View?) {
            trySend(Unit)
        }
    }
    this@clicksFlow.setOnClickListener(listener)
    awaitClose {
        this@clicksFlow.setOnClickListener(null)
    }
}