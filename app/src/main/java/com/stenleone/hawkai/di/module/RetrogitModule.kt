package com.stenleone.hawkai.di.module

import com.stenleone.hawkai.model.network.JsonPlaceHolderHawkAI
import com.stenleone.hawkai.util.constant.ApiConstant
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {
    factory { getPlaceHolderHawkAI() }
}

fun getPlaceHolderHawkAI(): JsonPlaceHolderHawkAI {
    var interceptor =  HttpLoggingInterceptor()

    val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl(ApiConstant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(
            RxJava2CallAdapterFactory
                .createWithScheduler(Schedulers.io()))
        .client(client)
        .build()

    val jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderHawkAI::class.java)
    return jsonPlaceHolderApi
}