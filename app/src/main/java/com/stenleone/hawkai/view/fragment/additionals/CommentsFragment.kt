package com.stenleone.hawkai.view.fragment.additionals

import com.stenleone.hawkai.R
import com.stenleone.hawkai.model.data.get.comments.Result
import com.stenleone.hawkai.model.view_model.CommentsPostViewModel
import com.stenleone.hawkai.util.easyInfo.makeToast
import com.stenleone.hawkai.view.fragment.base.BaseLoaderListContentFragment
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class CommentsFragment : BaseLoaderListContentFragment(R.layout.fragment_comments), KoinComponent {

    private val viewModel: CommentsPostViewModel by inject()
    private var postId = 0
    private var commentsResult: List<Result>? = null

    override fun initRecycler() {
        super.initRecycler()
        //adapter
    }

    override fun getContent() {
        super.getContent()
        viewModel.getCommentsPost(postId)
    }

    override fun onContentLoaded() {
        super.onContentLoaded()
        if (commentsResult != null) {
            makeToast(commentsResult.toString())
        }
    }

    override fun initAfterViewCreated() {

        postId = 11
        super.initAfterViewCreated()
    }

    override fun initViewModelCallBack() {

        viewModel.apply {

            liveComment.observe( viewLifecycleOwner, {
                commentsResult = it.results
                onContentLoaded()
            })

            liveError.observe( viewLifecycleOwner, {
                makeToast(it)
            })
        }
    }
}