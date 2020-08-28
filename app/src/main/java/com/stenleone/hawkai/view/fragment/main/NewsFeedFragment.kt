package com.stenleone.hawkai.view.fragment.main

import android.media.Image
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.stenleone.hawkai.R
import com.stenleone.hawkai.di.application.App
import com.stenleone.hawkai.model.data.get.post_news.Result
import com.stenleone.hawkai.model.view_model.PostNewsViewModel
import com.stenleone.hawkai.util.constant.ApiConstant
import com.stenleone.hawkai.util.easyInfo.makeToast
import com.stenleone.hawkai.view.fragment.base.BaseFragment
import com.stenleone.hawkai.view.adapter.recycler.ListNewsRecycler
import com.stenleone.hawkai.view.adapter.recycler.callback.CallBackFromListNews
import com.stfalcon.imageviewer.StfalconImageViewer
import kotlinx.android.synthetic.main.fragment_news_feed.*
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class NewsFeedFragment : BaseFragment(R.layout.fragment_news_feed), CallBackFromListNews,
    KoinComponent {

    private val viewModel: PostNewsViewModel by inject()
    private val adapterListNews: ListNewsRecycler by inject()

    private fun initSwipeToRefresh() {
        swipeToRefreshLay.setOnRefreshListener {
            getContent()
        }
    }

    private fun initRecycler() {
        recycler.layoutManager = LinearLayoutManager(context)
        adapterListNews.listener = this
        recycler.adapter = adapterListNews
    }

    private fun getContent() {
        viewModel.getPostsNews()
        animLoader(true)
    }

    private fun onContentLoaded(result: List<Result>?) {
        animLoader(false)
        swipeToRefreshLay.isRefreshing = false
        if (result != null) {
            (recycler.adapter as ListNewsRecycler).apply {
                arrayView = ArrayList(result)
                notifyDataSetChanged()
            }
        }
    }

    override fun initAfterViewCreated() {
        initRecycler()
        initSwipeToRefresh()
        getContent()

        val images:List<String> = listOf(
            "https://dev.hawkaiapp.com/media/profile/2020/08/26/elon.jpg"
        )

        StfalconImageViewer.Builder<String>(context, images) { view, im ->
            Glide
                .with(App.appContext)
                .load(im)
                .into(im_test)
        }.show()

    }

    override fun initViewModelCallBack() {

        viewModel.apply {

            livePost.observe(viewLifecycleOwner, {
                onContentLoaded(it.results)
            })

            liveError.observe(viewLifecycleOwner, {
                makeToast(ApiConstant.ERROR_TEXT + it)
                onContentLoaded(null)
            })
        }
    }

    override fun userClick() {
        makeToast("1")
    }

    override fun joinClick() {
        makeToast("2")
    }

    override fun imageClick() {
        makeToast("3")
    }
}