package com.stenleone.hawkai.model.data.get.comments

data class Result(
    val author: Author,
    val childs: List<Child>,
    val comment: String,
    val create_date: String,
    val id: Int,
    val images: List<Any>,
    val is_liked: Boolean,
    val level: Int,
    val likes: Int,
    val tags: List<Any>
)