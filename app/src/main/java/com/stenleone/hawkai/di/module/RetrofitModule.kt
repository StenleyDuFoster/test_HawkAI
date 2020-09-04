package com.stenleone.hawkai.di.module

import android.content.Context
import com.stenleone.hawkai.model.network.JsonPlaceHolderHawkAI
import com.stenleone.hawkai.model.network.interceptor.CashInterceptor
import com.stenleone.hawkai.model.network.interceptor.HeaderTokenInterceptor
import com.stenleone.hawkai.util.constant.ApiConstant

import io.reactivex.schedulers.Schedulers
import okhttp3.Cache

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext

import org.koin.dsl.module

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {
    single { getListInterceptor() }
    single { getOkkHttp(get(), androidContext()) }
    single { getRetrofit(get()) }
    single { getPlaceHolderHawkAI(get()) }
}

fun getListInterceptor(): List<Interceptor> {

    var tokenInterceptor = HeaderTokenInterceptor()
    var debugInterceptor = HttpLoggingInterceptor()
    var cashInterceptor = CashInterceptor()

    debugInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

    return listOf(
        tokenInterceptor,
        debugInterceptor,
        cashInterceptor
    )
}

fun getOkkHttp(listInterceptor: List<Interceptor>, context: Context): OkHttpClient {
    val client = OkHttpClient.Builder()
    val cacheSize = (5 * 1024 * 1024).toLong()
    val myCache = Cache(context.cacheDir, cacheSize)
    client.cache(myCache)

    for (interceptor in listInterceptor) {
        client.addInterceptor(interceptor)
    }

    return client.build()
}

fun getRetrofit(client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(ApiConstant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(
            RxJava2CallAdapterFactory
                .createWithScheduler(Schedulers.io())
        )
        .client(client)
        .build()
}

fun getPlaceHolderHawkAI(retrofit: Retrofit): JsonPlaceHolderHawkAI {
    return retrofit.create(JsonPlaceHolderHawkAI::class.java)
}