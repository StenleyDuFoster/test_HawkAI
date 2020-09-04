package com.stenleone.hawkai.model.view_model

import androidx.lifecycle.MutableLiveData
import com.stenleone.hawkai.model.data.get.comments.CommentsEntity
import com.stenleone.hawkai.model.data.get.comments.Result

import com.stenleone.hawkai.model.network.JsonPlaceHolderHawkAI
import com.stenleone.hawkai.model.view_model.base.BaseViewModel
import com.stenleone.hawkai.util.constant.ApiConstant

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class CommentsPostViewModel(private val jsonPlaceHolderFitPlan: JsonPlaceHolderHawkAI) : BaseViewModel() {

    val liveComment = MutableLiveData<ArrayList<Result>>()

    fun getCommentsPost(postId: Int) {

        jsonPlaceHolderFitPlan.getComments(postId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    if (it.isSuccessful) {
                        liveComment.postValue(addChildResultToResults(it.body()))
                    } else {
                        liveError.postValue(ApiConstant.ERROR_TEXT + it.code())
                    }
                },
                {
                    liveError.postValue(it.message)
                }
            ).addTo(compositeDisposable)
    }

    private fun addChildResultToResults(response: CommentsEntity?): ArrayList<Result> {
        val array = ArrayList(response?.results)

        var a = 0
        while(array.size > a) {
            if(!array[a].childs.isNullOrEmpty()) {
                array.addAll(array[a].childs.reversed())
            }
            a += 1
        }

        return array
    }
}