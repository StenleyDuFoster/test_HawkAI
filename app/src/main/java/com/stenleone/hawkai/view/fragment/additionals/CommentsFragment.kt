package com.stenleone.hawkai.view.fragment.additionals

import com.stenleone.hawkai.R
import com.stenleone.hawkai.model.view_model.CommentsPostViewModel
import com.stenleone.hawkai.util.easyInfo.makeToast
import com.stenleone.hawkai.view.activity.MainActivity
import com.stenleone.hawkai.model.data.get.comments.Result
import com.stenleone.hawkai.view.adapter.recycler.list_comments.ListCommentsRecycler
import com.stenleone.hawkai.view.adapter.recycler.callback.CallBackFromListComments
import com.stenleone.hawkai.view.fragment.base.BaseLoaderListContentFragment

import kotlinx.android.synthetic.main.fragment_news_feed.*

import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class CommentsFragment : BaseLoaderListContentFragment(R.layout.fragment_comments), CallBackFromListComments, KoinComponent {

    private val viewModel: CommentsPostViewModel by inject()
    private val adapterListCommentsRecycler: ListCommentsRecycler by inject()
    var postId = 0

    private var commentsResult: List<Result>? = null

    override fun initRecycler() {
        super.initRecycler()
        adapterListCommentsRecycler.listener = this
        recycler.adapter = adapterListCommentsRecycler
    }

    override fun getContent() {
        super.getContent()
        viewModel.getCommentsPost(postId)
    }

    override fun onContentLoaded() {
        super.onContentLoaded()
        if (commentsResult != null) {
            (recycler.adapter as ListCommentsRecycler).apply {
                arrayView = ArrayList(commentsResult)
                notifyDataSetChanged()
            }
        }
    }

    override fun initAfterViewCreated() {

        (activity as MainActivity).let {
            it.showBackButton()
            it.hideNavBar()
        }
        super.initAfterViewCreated()
    }

    override fun initViewModelCallBack() {

        viewModel.apply {

            liveComment.observe(viewLifecycleOwner, {
                commentsResult = it
                onContentLoaded()
            })

            liveError.observe(viewLifecycleOwner, {
                makeToast(it)
                commentsResult = null
                onContentLoaded()
            })
        }
    }

    override fun likeClick(adapterPosition: Int) {
        makeToast("1")
    }

    override fun userClick(adapterPosition: Int) {
        makeToast("2")
    }

    override fun replyClick(adapterPosition: Int) {
        makeToast("3")
    }
}