package com.stenleone.hawkai.model.data.get.post_news

import com.google.gson.annotations.SerializedName

data class Quiz(
    val string1: String,
    @SerializedName("stringa")
    val string2: String
)