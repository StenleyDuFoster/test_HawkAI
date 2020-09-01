package com.stenleone.hawkai.model.data.get.comments

data class CommentsEntity(
    val count: Int,
    val next: Any,
    val previous: Any,
    val results: List<Result>
)