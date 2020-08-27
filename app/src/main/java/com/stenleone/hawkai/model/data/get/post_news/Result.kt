package com.stenleone.hawkai.model.data.get.post_news

data class Result(
    val approved_status: String,
    val author: Author,
    val comments_count: Int,
    val create_date: String,
    val id: Int,
    val images: List<Image>,
    val latest_comments: List<LatestComment>,
    val location: Location,
    val location_name: String,
    val quiz: Quiz,
    val reviewers: List<Reviewer>,
    val tags: List<String>,
    val text: String
)