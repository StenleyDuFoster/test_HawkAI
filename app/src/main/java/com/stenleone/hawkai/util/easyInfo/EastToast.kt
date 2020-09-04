package com.stenleone.hawkai.util.easyInfo

import android.content.Context
import android.widget.Toast
import com.stenleone.hawkai.App

fun Context.makeToast(message: String) {
    Toast.makeText(App.appContext, message, Toast.LENGTH_SHORT).show()
}

fun makeToast(message: String) {
    Toast.makeText(App.appContext, message, Toast.LENGTH_SHORT).show()
}