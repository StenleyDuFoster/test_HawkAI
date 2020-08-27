package com.stenleone.hawkai.model.data.get.post_news

data class LatestComment(
    val author: AuthorX,
    val childs: List<Any>,
    val comment: String,
    val create_date: String,
    val id: Int,
    val images: List<ImageX>,
    val is_liked: Boolean,
    val level: Int,
    val likes: Int,
    val parent: Int,
    val tags: List<String>
)