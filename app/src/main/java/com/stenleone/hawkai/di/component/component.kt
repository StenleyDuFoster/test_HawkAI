package com.stenleone.hawkai.di.component

import com.stenleone.hawkai.di.module.adapterModule
import com.stenleone.hawkai.di.module.retrofitModule
import com.stenleone.hawkai.di.module.viewModelModule

val appComponent = listOf(
    retrofitModule,
    viewModelModule,
    adapterModule
)