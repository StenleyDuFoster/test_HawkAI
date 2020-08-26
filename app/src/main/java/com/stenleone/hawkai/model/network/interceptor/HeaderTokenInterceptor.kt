package com.stenleone.hawkai.model.network.interceptor

import com.stenleone.hawkai.util.shared_preferences.SharedPreferencesManager
import okhttp3.Interceptor
import okhttp3.Response

class HeaderTokenInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request()
            .newBuilder()
            .addHeader("Content-Type", "application/json")
        var accessToken = SharedPreferencesManager.getToken()

        if (accessToken == null) {
            chain.request()
        } else {
            builder.addHeader("Authorization", "Bearer ${accessToken}")
        }

        return chain.proceed(builder.build())
    }
}