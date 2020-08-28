package com.stenleone.hawkai.di.module

import com.stenleone.hawkai.view.recycler.ListNewsRecycler
import org.koin.dsl.module.module

val adapterModule = module {
    factory { ListNewsRecycler() }
}