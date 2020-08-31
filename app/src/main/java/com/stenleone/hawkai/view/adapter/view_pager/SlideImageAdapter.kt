package com.stenleone.hawkai.view.adapter.view_pager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jakewharton.rxbinding3.view.clicks
import com.stenleone.hawkai.R
import com.stenleone.hawkai.di.application.App
import com.stenleone.hawkai.model.data.get.post_news.Image
import com.stenleone.hawkai.view.adapter.view_pager.hud.HudImageLay
import com.stfalcon.imageviewer.StfalconImageViewer
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.item_page.view.*
import java.util.concurrent.TimeUnit

class SlideImageAdapter : RecyclerView.Adapter<SlideImageAdapter.PagerVH>() {

    lateinit var listImage: List<Image>
    private val compositeDisposable = CompositeDisposable()

    inner class PagerVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.apply {
                compositeDisposable.add(
                    image.clicks()
                        .throttleFirst(1, TimeUnit.SECONDS)
                        .subscribe {
                            buildImageViewer(context, position, image)
                        })
            }
        }
    }

    private fun buildImageViewer(context: Context, position: Int, startImage: ImageView) {

        val hudImageLay = HudImageLay(context, position, listImage.size)

        StfalconImageViewer.Builder(
            context,
            listImage
        ) { fullScreenImageView, fullScreenList ->

            Glide
                .with(App.appContext)
                .load(fullScreenList.image)
                .override(1000)
                .centerCrop()
                .into(fullScreenImageView)
        }
            .withOverlayView(hudImageLay)
            .withImageChangeListener { hudImageLay.update(it) }
            .withStartPosition(position)
            .withTransitionFrom(startImage)
            .show()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH =
        PagerVH(LayoutInflater.from(parent.context).inflate(R.layout.item_page, parent, false))

    override fun getItemCount(): Int = listImage.size

    override fun onBindViewHolder(holder: PagerVH, position: Int): Unit = holder.itemView.run {
        Glide
            .with(App.appContext)
            .load(listImage[position].image)
            .centerCrop()
            .into(image)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        compositeDisposable.dispose()
    }
}