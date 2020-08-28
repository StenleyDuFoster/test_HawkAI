package com.stenleone.hawkai.view.adapter.view_pager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.stenleone.hawkai.R
import com.stenleone.hawkai.di.application.App
import com.stenleone.hawkai.model.data.get.post_news.Image
import kotlinx.android.synthetic.main.item_page.view.*

class SlideImageAdapter : RecyclerView.Adapter<PagerVH>() {

    lateinit var images_url: List<Image>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH =
        PagerVH(LayoutInflater.from(parent.context).inflate(R.layout.item_page, parent, false))

    override fun getItemCount(): Int = images_url.size

    override fun onBindViewHolder(holder: PagerVH, position: Int): Unit = holder.itemView.run {
        Glide
            .with(App.appContext)
            .load(images_url[position].image)
            .centerCrop()
            .into(image)
    }
}

class PagerVH(itemView: View) : RecyclerView.ViewHolder(itemView)