package com.stenleone.hawkai.model.data.get.post_news

import com.google.gson.annotations.SerializedName
import com.stenleone.hawkai.model.data.get.standart.Author

data class Result(
    @SerializedName("approved_status")
    val approvedStatus: String,
    val author: Author,
    @SerializedName("comments_count")
    val commentsCount: Int,
    @SerializedName("create_date")
    val createDate: String,
    val id: Int,
    val images: List<Image>,
    @SerializedName("latest_comments")
    val latestComments: List<LatestComment>,
    val location: Location,
    val location_name: String,
    val quiz: Quiz,
    val reviewers: List<Reviewer>,
    val tags: List<String>,
    val text: String
)