package com.stenleone.hawkai.model.network

import com.stenleone.hawkai.model.data.get.login.LoginHawkAIResponseEntity
import com.stenleone.hawkai.model.data.get.post_news.PostResponseEntity
import com.stenleone.hawkai.model.data.post.PostAuth
import com.stenleone.hawkai.util.constant.ApiConstant
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface JsonPlaceHolderHawkAI {

    @POST(ApiConstant.LOGIN_URL)
    fun postLogin(@Body auth: PostAuth): Single<Response<LoginHawkAIResponseEntity>>

    @GET(ApiConstant.POST_URL)
    fun getPosts(): Single<Response<PostResponseEntity>>
}