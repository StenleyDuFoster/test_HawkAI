package com.stenleone.hawkai.model.network

import com.stenleone.hawkai.model.data.get.comments.CommentsEntity
import com.stenleone.hawkai.model.data.get.login.LoginHawkAIResponseEntity
import com.stenleone.hawkai.model.data.get.post_news.PostResponseEntity
import com.stenleone.hawkai.model.data.post.PostAuth
import com.stenleone.hawkai.util.constant.ApiConstant
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface JsonPlaceHolderHawkAI {

    @POST(ApiConstant.LOGIN_URL)
    fun postLogin(@Body auth: PostAuth): Single<Response<LoginHawkAIResponseEntity>>

    @GET(ApiConstant.POST_URL + ApiConstant.LIMIT)
    fun getPosts(): Single<Response<PostResponseEntity>>

    @GET(ApiConstant.POST_URL + "{post_number}/" + ApiConstant.COMMENT_URL + ApiConstant.LIMIT)
    fun getComments(
        @Path("post_number") post_number: Int,
    ): Single<Response<CommentsEntity>>
}