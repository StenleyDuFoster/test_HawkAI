package com.stenleone.hawkai.view.fragment.main

import com.stenleone.hawkai.R
import com.stenleone.hawkai.model.view_model.PostNewsViewModel
import com.stenleone.hawkai.util.constant.ApiConstant
import com.stenleone.hawkai.util.easyInfo.makeToast
import com.stenleone.hawkai.view.fragment.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_news_feed.*
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class NewsFeedFragment : BaseFragment(R.layout.fragment_news_feed), KoinComponent {

    private val viewModel: PostNewsViewModel by inject()

    fun initSwipeToRefresh() {
        swipeToRefreshLay.setOnRefreshListener {
            getContent()
        }
    }

    fun getContent() {
        viewModel.getPostsNews()
        animLoader(true)
    }

    fun onContentLoaded() {
        animLoader(false)
        swipeToRefreshLay.isRefreshing = false
    }

    override fun initAfterViewCreated() {

        getContent()
        initSwipeToRefresh()
    }

    override fun initViewModelCallBack() {

        viewModel.apply {

            livePost.observe(viewLifecycleOwner, {
                makeToast(it.results.size.toString())
                onContentLoaded()
            })

            liveError.observe(viewLifecycleOwner, {
                makeToast(ApiConstant.ERROR_TEXT + it)
                onContentLoaded()
            })
        }
    }
}