package com.stenleone.hawkai.model.view_model.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    val compositeDisposable = CompositeDisposable()

    val liveError = MutableLiveData<String>()

    override fun onCleared() {
        if(!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
        super.onCleared()
    }
}