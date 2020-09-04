package com.stenleone.hawkai.view.fragment.main

import com.stenleone.hawkai.R
import com.stenleone.hawkai.model.data.get.post_news.Result
import com.stenleone.hawkai.model.view_model.PostNewsViewModel
import com.stenleone.hawkai.util.constant.ApiConstant
import com.stenleone.hawkai.util.easyInfo.makeToast
import com.stenleone.hawkai.view.activity.MainActivity
import com.stenleone.hawkai.view.adapter.recycler.ListNewsRecycler
import com.stenleone.hawkai.view.adapter.recycler.callback.CallBackFromListNews
import com.stenleone.hawkai.view.fragment.additionals.CommentsFragment
import com.stenleone.hawkai.view.fragment.base.BaseLoaderListContentFragment

import kotlinx.android.synthetic.main.fragment_news_feed.*

import org.koin.android.ext.android.inject

class NewsFeedFragment : BaseLoaderListContentFragment(R.layout.fragment_news_feed),
    CallBackFromListNews {

    private val viewModel: PostNewsViewModel by inject()
    private val adapterListNews: ListNewsRecycler by inject()
    private var postResult: ArrayList<Result> = ArrayList()

    override fun initSwipeToRefresh() {
        swipeToRefreshLay.setOnRefreshListener {
            getContent()
            postResult = ArrayList()
        }
    }

    override fun initRecycler() {
        super.initRecycler()
        adapterListNews.listener = this
        recycler.adapter = adapterListNews
        (recycler.adapter as ListNewsRecycler).arrayView = postResult
    }

    override fun getContent() {
        super.getContent()
        viewModel.getPostsNews()
    }

    override fun onContentLoaded() {
        super.onContentLoaded()
        (recycler.adapter as ListNewsRecycler).notifyDataSetChanged()
    }

    override fun initViewModelCallBack() {

        viewModel.apply {

            livePost.observe(viewLifecycleOwner, {
                postResult.addAll(it.results)
                onContentLoaded()
            })

            liveError.observe(viewLifecycleOwner, {
                makeToast(ApiConstant.ERROR_TEXT + it)
                onContentLoaded()
            })
        }
    }

    override fun userClick(adapterPosition: Int) {
        makeToast(adapterPosition.toString())
    }

    override fun joinClick(adapterPosition: Int) {
        (activity as MainActivity).let {
            it.commentsFragment = CommentsFragment()
            it.commentsFragment?.postId = adapterPosition
            it.fragmentManager.addWithBackStackFragmentToFragmentManager(
                it.commentsFragment!!
            )
        }
    }
}