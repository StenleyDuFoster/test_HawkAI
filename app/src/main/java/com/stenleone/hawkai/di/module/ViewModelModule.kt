package com.stenleone.hawkai.di.module

import com.stenleone.hawkai.model.view_model.LoginViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val viewModelModule : Module = module {

    viewModel { LoginViewModel(get()) }
}