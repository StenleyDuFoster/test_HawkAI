package com.stenleone.hawkai.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager

import com.stenleone.hawkai.util.easyInfo.makeToast

class NetworkChangeReceiver : BroadcastReceiver() {

    companion object {
        var networkState = true
    }

    private var oldNetworkState = true

    override fun onReceive(context: Context?, intent: Intent) {

        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if(cm.activeNetworkInfo == null)
        {
            networkState = false

            if(networkState != oldNetworkState) {
                context.makeToast("соединение потеряно")
            }

            oldNetworkState = networkState
        }
        else
        {
            networkState = true

            if(networkState != oldNetworkState) {
                context.makeToast("соединение востановлено")
            }

            oldNetworkState = networkState
        }
    }
}