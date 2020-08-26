package com.stenleone.hawkai.model.view_model.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stenleone.hawkai.model.network.JsonPlaceHolderHawkAI
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

abstract class BaseViewModel : ViewModel(), KoinComponent {

    val liveError = MutableLiveData<String>()

    fun getError() = liveError
}