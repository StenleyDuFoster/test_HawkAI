package com.stenleone.hawkai.view.fragment.additionals

import android.content.Intent
import android.graphics.Bitmap
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.view.updateMargins
import com.jakewharton.rxbinding3.view.clicks
import com.stenleone.hawkai.R
import com.stenleone.hawkai.App
import com.stenleone.hawkai.model.data.get.comments.Result
import com.stenleone.hawkai.model.view_model.CommentsPostViewModel
import com.stenleone.hawkai.util.constant.IntentConstant
import com.stenleone.hawkai.util.easyInfo.makeToast
import com.stenleone.hawkai.util.glide.GlideApp
import com.stenleone.hawkai.util.intent_media_manager.IntentMediaManager
import com.stenleone.hawkai.view.activity.MainActivity
import com.stenleone.hawkai.view.adapter.recycler.callback.CallBackFromListComments
import com.stenleone.hawkai.view.adapter.recycler.list_comments.ListCommentsRecycler
import com.stenleone.hawkai.view.fragment.base.BaseLoaderListContentFragment
import kotlinx.android.synthetic.main.fragment_news_feed.*
import kotlinx.android.synthetic.main.nav_send_system.*
import org.koin.android.ext.android.inject
import java.util.concurrent.TimeUnit

class CommentsFragment : BaseLoaderListContentFragment(R.layout.fragment_comments),
    CallBackFromListComments {

    private val viewModel: CommentsPostViewModel by inject()
    private val adapterListCommentsRecycler: ListCommentsRecycler by inject()
    val intentMediaManager = IntentMediaManager(this)
    var postId = 0

    private var commentsResult: ArrayList<Result> = ArrayList()

    private fun initNavButtons() {

        disposable.add(
            navGallery.clicks()
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe {
                    intentMediaManager.createGalleryIntent()
                }
        )

        disposable.add(
            navPhoto.clicks()
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe {
                    intentMediaManager.createCameraWithPermission()
                }
        )

        disposable.add(
            navTag.clicks()
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe {
                    makeToast(getString(R.string.develop))
                }
        )

        disposable.add(
            navUserIco.clicks()
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe {
                    makeToast(getString(R.string.develop))
                }
        )

        disposable.add(
            navSend.clicks()
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe {
                    makeToast(getString(R.string.develop))
                }
        )
    }

    private fun createImageViewForResult(): ImageView {

        val imageView = ImageView(context)
        val layParams = LinearLayout.LayoutParams(
            200,
            200
        )
        layParams.updateMargins(10,10,10,10)
        imageView.layoutParams = layParams

        disposable.add(
            imageView.clicks().throttleFirst(1, TimeUnit.SECONDS)
                .subscribe {
                    chipGroup.removeView(imageView)
                }
        )
        return imageView
    }

    override fun initSwipeToRefresh() {
        swipeToRefreshLay.setOnRefreshListener {
            getContent()
            commentsResult = ArrayList()
        }
    }

    override fun initRecycler() {
        super.initRecycler()
        adapterListCommentsRecycler.listener = this
        adapterListCommentsRecycler.arrayView = commentsResult
        recycler.adapter = adapterListCommentsRecycler
    }

    override fun getContent() {
        super.getContent()
        viewModel.getCommentsPost(postId)
    }

    override fun onContentLoaded() {
        super.onContentLoaded()
        (recycler.adapter as ListCommentsRecycler).notifyDataSetChanged()
    }

    override fun initAfterViewCreated() {

        (activity as MainActivity).let {
            it.showBackButton()
            it.hideNavBar()
        }

        super.initAfterViewCreated()
        initNavButtons()
    }

    override fun initViewModelCallBack() {

        viewModel.apply {

            liveComment.observe(viewLifecycleOwner, {
                commentsResult.addAll(it)
                onContentLoaded()
            })

            liveError.observe(viewLifecycleOwner, {
                makeToast(it)
                onContentLoaded()
            })
        }
    }

    override fun likeClick(adapterPosition: Int) {
        makeToast(getString(R.string.develop))
    }

    override fun userClick(adapterPosition: Int) {
        makeToast(getString(R.string.develop))
    }

    override fun replyClick(adapterPosition: Int) {
        makeToast(getString(R.string.develop))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (data != null) {

            val imageView = createImageViewForResult()
            chipGroup.addView(imageView)

            when (requestCode) {
                IntentConstant.CAMERA -> {
                    val image = data!!.extras!!.get("data") as Bitmap
                    imageView.setImageBitmap(image)
                }
                IntentConstant.GALLERY -> {
                    GlideApp
                        .with(App.appContext)
                        .load(data.data)
                        .centerCrop()
                        .into(imageView)
                }
            }
        }
    }
}