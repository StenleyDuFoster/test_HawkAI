package com.stenleone.hawkai.view.fragment.main

import com.stenleone.hawkai.R
import com.stenleone.hawkai.model.data.get.post_news.Result
import com.stenleone.hawkai.model.view_model.PostNewsViewModel
import com.stenleone.hawkai.util.constant.ApiConstant
import com.stenleone.hawkai.util.easyInfo.makeToast
import com.stenleone.hawkai.view.activity.base.BaseActivity
import com.stenleone.hawkai.view.adapter.recycler.ListNewsRecycler
import com.stenleone.hawkai.view.adapter.recycler.callback.CallBackFromListNews
import com.stenleone.hawkai.view.fragment.additionals.CommentsFragment
import com.stenleone.hawkai.view.fragment.base.BaseLoaderListContentFragment

import kotlinx.android.synthetic.main.fragment_news_feed.*

import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class NewsFeedFragment : BaseLoaderListContentFragment(R.layout.fragment_news_feed), CallBackFromListNews,
    KoinComponent {

    private val viewModel: PostNewsViewModel by inject()
    private val adapterListNews: ListNewsRecycler by inject()
    private var postResult: List<Result>? = null

    override fun initRecycler() {
        super.initRecycler()
        adapterListNews.listener = this
        recycler.adapter = adapterListNews
    }

    override fun getContent() {
        super.getContent()
        viewModel.getPostsNews()
    }

    override fun onContentLoaded() {
        super.onContentLoaded()
        if (postResult != null) {
            (recycler.adapter as ListNewsRecycler).apply {
                arrayView = ArrayList(postResult)
                notifyDataSetChanged()
            }
        }
    }

    override fun initViewModelCallBack() {

        viewModel.apply {

            livePost.observe(viewLifecycleOwner, {
                postResult = it.results
                onContentLoaded()
            })

            liveError.observe(viewLifecycleOwner, {
                makeToast(ApiConstant.ERROR_TEXT + it)
                postResult = null
                onContentLoaded()
            })
        }
    }

    override fun userClick() {
        makeToast("1")
    }

    override fun joinClick() {
        activity.let {
            val commentsFragment = CommentsFragment()
            (it as BaseActivity).fragmentManager.addWithBackStackFragmentToFragmentManager(
                commentsFragment,
                this
            )
        }
    }
}