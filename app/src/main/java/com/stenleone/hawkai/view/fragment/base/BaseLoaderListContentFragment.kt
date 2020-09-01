package com.stenleone.hawkai.view.fragment.base

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_news_feed.*

abstract class BaseLoaderListContentFragment(layId: Int) : BaseFragment(layId) {

    open fun initSwipeToRefresh() {
        swipeToRefreshLay.setOnRefreshListener {
            getContent()
        }
    }

    open fun initRecycler() {
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.setItemViewCacheSize(10)
    }

    open fun getContent() {
        animLoader(true)
    }

    open fun onContentLoaded() {
        animLoader(false)
        swipeToRefreshLay.isRefreshing = false
    }

    override fun initAfterViewCreated() {
        initRecycler()
        initSwipeToRefresh()
        getContent()
    }
}