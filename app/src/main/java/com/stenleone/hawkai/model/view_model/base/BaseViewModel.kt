package com.stenleone.hawkai.model.view_model.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stenleone.hawkai.model.network.JsonPlaceHolderHawkAI
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

abstract class BaseViewModel : ViewModel(), KoinComponent {

    protected val liveError = MutableLiveData<String>()

    protected val jsonPlaceHolderFitPlan: JsonPlaceHolderHawkAI by inject()

    fun getError() = liveError
}