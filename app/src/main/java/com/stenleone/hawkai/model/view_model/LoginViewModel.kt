package com.stenleone.hawkai.model.view_model

import androidx.lifecycle.MutableLiveData
import com.stenleone.hawkai.BuildConfig

import com.stenleone.hawkai.model.data.get.LoginHawkAIResponseEntity
import com.stenleone.hawkai.model.data.post.PostAuth
import com.stenleone.hawkai.model.network.JsonPlaceHolderHawkAI
import com.stenleone.hawkai.model.view_model.base.BaseViewModel
import com.stenleone.hawkai.util.constant.ApiConstant
import com.stenleone.hawkai.util.shared_preferences.SharedPreferencesManager

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginViewModel(val jsonPlaceHolderFitPlan: JsonPlaceHolderHawkAI) : BaseViewModel() {

    val liveData = MutableLiveData<LoginHawkAIResponseEntity>()

    fun loginHawkAI(password: String) {

        jsonPlaceHolderFitPlan.postLogin(
            PostAuth(
                ApiConstant.PHONE,
                password,
                BuildConfig.VERSION_CODE
            )
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    if (it.isSuccessful) {
                        liveData.postValue(it.body())
                        SharedPreferencesManager.setToken(it.body()?.token)
                    } else {
                        liveError.postValue(ApiConstant.ERROR_TEXT + it.code())
                    }
                },
                {
                    liveError.postValue(it.message)
                }
            )
    }
}