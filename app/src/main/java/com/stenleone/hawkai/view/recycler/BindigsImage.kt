package com.stenleone.hawkai.view.recycler

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("loadCircleImage")
fun loadCircleImage(view: ImageView, imageUrl: String?) {
    if(imageUrl != null) {
        view.visibility = View.VISIBLE
        Glide.with(view.context)
            .load(imageUrl).apply(RequestOptions().circleCrop())
            .into(view)
    } else {
        view.visibility = View.GONE
    }
}

@BindingAdapter("loadImage")
fun loadImage(view: ImageView, imageUrl: String?) {
    if(imageUrl != null) {
        view.visibility = View.VISIBLE
        Glide.with(view.context)
            .load(imageUrl)
            .centerCrop()
            .into(view)
    } else {
        view.visibility = View.GONE
    }
}

@BindingAdapter("getComment")
fun getComment(view: TextView, comment: Int) {
    if(comment > 0) {
        view.text = comment.toString() + " comments"
    } else {
        view.text = ""
    }
}