package com.stenleone.hawkai.model.network

import com.stenleone.hawkai.model.data.get.comments.CommentsEntity
import com.stenleone.hawkai.model.data.get.login.LoginHawkAIResponseEntity
import com.stenleone.hawkai.model.data.get.post_news.PostResponseEntity
import com.stenleone.hawkai.model.data.post.PostAuth
import com.stenleone.hawkai.util.constant.ApiConstant
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

interface JsonPlaceHolderHawkAI {

    @POST(ApiConstant.LOGIN_URL)
    fun postLogin(@Body auth: PostAuth): Single<Response<LoginHawkAIResponseEntity>>

    @GET(ApiConstant.POST_URL)
    fun getPosts(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): Single<Response<PostResponseEntity>>

    @GET(ApiConstant.COMMENT_URL)
    fun getComments(
        @Path("post_number") post_number: Int,
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): Single<Response<CommentsEntity>>
}