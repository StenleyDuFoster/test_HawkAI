package com.stenleone.hawkai.model.view_model

import androidx.lifecycle.MutableLiveData

import com.stenleone.hawkai.model.data.LoginHawkAIResponseEnity
import com.stenleone.hawkai.model.view_model.base.BaseViewModel
import com.stenleone.hawkai.util.constant.ApiConstant

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginViewModel : BaseViewModel() {

    private val liveData = MutableLiveData<LoginHawkAIResponseEnity>()

    fun getData() = liveData
    fun loginHawkAI(password: Int) {

        jsonPlaceHolderFitPlan.postLogin(
            ApiConstant.PHONE,
            password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    if(it.isSuccessful){
                        liveData.postValue(it.body())
                    } else {
                        liveError.postValue(it.message())
                    }
                },
                {
                    liveError.postValue(it.message)
                }
            )
    }
}