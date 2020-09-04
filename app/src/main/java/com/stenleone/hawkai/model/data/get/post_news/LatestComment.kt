package com.stenleone.hawkai.model.data.get.post_news

import com.google.gson.annotations.SerializedName
import com.stenleone.hawkai.model.data.get.standart.Author

data class LatestComment(
    val author: Author,
    @SerializedName("childs")
    val child: List<Any>,
    val comment: String,
    @SerializedName("create_date")
    val createDate: String,
    val id: Int,
    val images: List<Image>,
    @SerializedName("is_liked")
    val isLiked: Boolean,
    val level: Int,
    val likes: Int,
    val parent: Int,
    val tags: List<String>
)