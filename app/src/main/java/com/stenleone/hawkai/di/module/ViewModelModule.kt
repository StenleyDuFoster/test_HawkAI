package com.stenleone.hawkai.di.module

import com.stenleone.hawkai.model.view_model.CommentsPostViewModel
import com.stenleone.hawkai.model.view_model.LoginViewModel
import com.stenleone.hawkai.model.view_model.PostNewsViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val viewModelModule : Module = module {

    viewModel { LoginViewModel(get()) }
    viewModel { PostNewsViewModel(get()) }
    viewModel { CommentsPostViewModel(get()) }
}