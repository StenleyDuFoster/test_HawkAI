package com.stenleone.hawkai.di.module

import com.stenleone.hawkai.view.adapter.recycler.list_comments.ListCommentsRecycler
import com.stenleone.hawkai.view.adapter.recycler.ListNewsRecycler
import com.stenleone.hawkai.view.adapter.view_pager.SlideImageAdapter
import org.koin.dsl.module

val adapterModule = module {
    factory { ListNewsRecycler() }
    factory { ListCommentsRecycler() }
    factory { SlideImageAdapter() }
}