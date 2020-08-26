package com.stenleone.hawkai.util.easyToast

import android.widget.Toast
import com.stenleone.hawkai.di.application.App

fun makeToast(message: String) {
    Toast.makeText(App.appContext, message, Toast.LENGTH_SHORT).show()
}