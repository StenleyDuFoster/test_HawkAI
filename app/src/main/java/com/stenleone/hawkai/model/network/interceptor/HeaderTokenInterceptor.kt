package com.stenleone.hawkai.model.network.interceptor

import com.stenleone.hawkai.util.constant.ApiConstant
import com.stenleone.hawkai.util.shared_preferences.SharedPreferencesManager
import okhttp3.Interceptor
import okhttp3.Response

class HeaderTokenInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request()
            .newBuilder()
        var accessToken = SharedPreferencesManager.getToken()

        if (accessToken == null) {
            chain.request()
        } else {
            builder.addHeader(ApiConstant.AUTH, "${ApiConstant.TOKEN_PREFIX} ${accessToken}")
        }

        return chain.proceed(builder.build())
    }
}