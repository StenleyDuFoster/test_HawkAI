package com.stenleone.hawkai.model.network

import com.stenleone.hawkai.model.data.LoginHawkAIResponseEnity
import com.stenleone.hawkai.util.constant.ApiConstant
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface JsonPlaceHolderHawkAI {

    @POST(ApiConstant.LOGIN)
    fun postLogin(
        @Query("phone") phone: String,
        @Query("password") password: Int
    ): Single<Response<LoginHawkAIResponseEnity>>
}