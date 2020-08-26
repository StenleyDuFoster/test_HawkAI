package com.stenleone.hawkai.util.shared_preferences

import com.stenleone.hawkai.util.constant.SharedPreferencesConstant
import com.stenleone.hawkai.util.shared_preferences.base.BaseSharedPrefs

object SharedPreferencesManager : BaseSharedPrefs() {

    fun setToken(newToken: String?) {
        editor.putString(newToken, null)
        editor.apply()
    }

    fun getToken() = sharedPreferences.getString(SharedPreferencesConstant.TOKEN, null)
}