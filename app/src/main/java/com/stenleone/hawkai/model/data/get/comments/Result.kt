package com.stenleone.hawkai.model.data.get.comments

import com.google.gson.annotations.SerializedName
import com.stenleone.hawkai.model.data.get.standart.Author

data class Result(
    val author: Author,
    val childs: List<Result>,
    val comment: String,
    val create_date: String,
    val id: Int,
    val images: List<Any>,
    @SerializedName("is_liked")
    val isLiked: Boolean,
    val level: Int,
    val likes: Int,
    val parent: Any,
    val tags: List<Any>
)