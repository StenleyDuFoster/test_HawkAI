package com.stenleone.hawkai.util.easyInfo

import android.widget.Toast
import com.stenleone.hawkai.App

fun makeToast(message: String) {
    Toast.makeText(App.appContext, message, Toast.LENGTH_SHORT).show()
}