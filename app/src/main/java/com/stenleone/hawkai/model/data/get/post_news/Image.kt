package com.stenleone.hawkai.model.data.get.post_news

import com.google.gson.annotations.SerializedName

data class Image(
    val id: Int,
    val image: String,
    @SerializedName("image_height")
    val imageHeight: Int,
    @SerializedName("image_width")
    val imageWidth: Int
)