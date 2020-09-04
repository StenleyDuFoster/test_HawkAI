package com.stenleone.hawkai.util.extensions

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyboard() {
    val view = currentFocus
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
}

fun Activity.runActivity(intentClass: Class<*>) {
    val intent = Intent(this, intentClass)
    startActivity(intent)
}

fun Activity.runActivityWithFinish(intentClass: Class<*>) {
    runActivity(intentClass)
    finish()
}

fun Activity.initReceiver(networkReceiver: BroadcastReceiver) {
    val intentFilter = IntentFilter()
    intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
    registerReceiver(networkReceiver, intentFilter)
}