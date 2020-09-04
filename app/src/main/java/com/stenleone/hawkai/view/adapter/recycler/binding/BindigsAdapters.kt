package com.stenleone.hawkai.view.adapter.recycler.binding

import android.view.View
import android.widget.ImageView
import android.widget.TextView

import androidx.databinding.BindingAdapter

import com.bumptech.glide.request.RequestOptions
import com.stenleone.hawkai.util.glide.GlideApp
import java.text.SimpleDateFormat

@BindingAdapter("loadCircleImage")
fun loadCircleImage(view: ImageView, imageUrl: String?) {
    var url = imageUrl

    if (url == null) {
        url = "https://d32ogoqmya1dw8.cloudfront.net/images/serc/empty_user_icon_256.v2.png"
    }

    GlideApp
        .with(view.context)
        .load(url).apply(RequestOptions().circleCrop())
        .into(view)
}

@BindingAdapter("dateConverter")
fun dateConverter(view: TextView, dateText: String?) {
    if (dateText != null) {

        val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val output = SimpleDateFormat("dd MMM HH:mm")

        val date = input.parse(dateText)
        view.text = output.format(date)
    }
}

@BindingAdapter("loadImage")
fun loadImage(view: ImageView, imageUrl: String?) {
    if (imageUrl != null) {
        view.visibility = View.VISIBLE

        GlideApp
            .with(view.context)
            .load(imageUrl)
            .centerCrop()
            .into(view)

    } else {
        view.visibility = View.GONE
    }
}

@BindingAdapter("getComment")
fun getComment(view: TextView, comment: Int) {
    if (comment > 0) {
        view.text = comment.toString() + " comments"
    } else {
        view.text = ""
    }
}