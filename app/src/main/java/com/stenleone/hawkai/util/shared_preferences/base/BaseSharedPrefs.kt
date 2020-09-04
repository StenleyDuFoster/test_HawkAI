package com.stenleone.hawkai.util.shared_preferences.base

import android.content.Context
import com.stenleone.hawkai.App
import com.stenleone.hawkai.util.constant.SharedPreferencesConstant

abstract class BaseSharedPrefs {

    protected val sharedPreferences = App.appContext.getSharedPreferences(
        SharedPreferencesConstant.SHARED_NAME,
        Context.MODE_PRIVATE
    )
    protected val editor = sharedPreferences.edit()
}