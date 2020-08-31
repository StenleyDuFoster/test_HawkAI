package com.stenleone.hawkai.model.view_model

import androidx.lifecycle.MutableLiveData
import com.stenleone.hawkai.model.data.get.comments.CommentEntity

import com.stenleone.hawkai.model.network.JsonPlaceHolderHawkAI
import com.stenleone.hawkai.model.view_model.base.BaseViewModel
import com.stenleone.hawkai.util.constant.ApiConstant

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CommentsPostViewModel(val jsonPlaceHolderFitPlan: JsonPlaceHolderHawkAI) : BaseViewModel() {

    val liveComment = MutableLiveData<CommentEntity>()

    fun getCommentsPost(postId: Int) {

        jsonPlaceHolderFitPlan.getComments(postId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    if (it.isSuccessful) {
                        liveComment.postValue(it.body())
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