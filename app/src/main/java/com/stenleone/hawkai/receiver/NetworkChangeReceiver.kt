package com.stenleone.hawkai.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager

import com.stenleone.hawkai.util.easyInfo.makeToast

class NetworkChangeReceiver : BroadcastReceiver() {

    companion object {
        var newNetworkState = true
    }

    private var oldNetworkState = true

    override fun onReceive(context: Context?, intent: Intent) {

        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if(cm.activeNetworkInfo == null)
        {
            newNetworkState = false

            if(newNetworkState != oldNetworkState) {
                makeToast("соединение потеряно")
            }

            oldNetworkState = newNetworkState
        }
        else
        {
            newNetworkState = true

            if(newNetworkState != oldNetworkState) {
                makeToast("соединение востановлено")
            }

            oldNetworkState = newNetworkState
        }
    }
}