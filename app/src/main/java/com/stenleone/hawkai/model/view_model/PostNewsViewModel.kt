package com.stenleone.hawkai.model.view_model

import androidx.lifecycle.MutableLiveData
import com.stenleone.hawkai.BuildConfig

import com.stenleone.hawkai.model.data.get.login.LoginHawkAIResponseEntity
import com.stenleone.hawkai.model.data.get.post_news.PostResponseEntity
import com.stenleone.hawkai.model.data.post.PostAuth
import com.stenleone.hawkai.model.network.JsonPlaceHolderHawkAI
import com.stenleone.hawkai.model.view_model.base.BaseViewModel
import com.stenleone.hawkai.util.constant.ApiConstant
import com.stenleone.hawkai.util.shared_preferences.SharedPreferencesManager

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PostNewsViewModel(val jsonPlaceHolderFitPlan: JsonPlaceHolderHawkAI) : BaseViewModel() {

    val livePost = MutableLiveData<PostResponseEntity>()

    fun getPostsNews() {

        jsonPlaceHolderFitPlan.getPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    if (it.isSuccessful) {
                        livePost.postValue(it.body())
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