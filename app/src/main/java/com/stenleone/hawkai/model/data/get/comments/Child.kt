package com.stenleone.hawkai.model.data.get.comments

data class Child(
    val author: AuthorX,
    val comment: String,
    val create_date: String,
    val id: Int,
    val images: List<Any>,
    val is_liked: Boolean,
    val level: Int,
    val likes: Int,
    val parent: Int,
    val tags: List<Any>
)