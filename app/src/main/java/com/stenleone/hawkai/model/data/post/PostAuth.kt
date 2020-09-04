package com.stenleone.hawkai.model.data.post

import com.google.gson.annotations.SerializedName

data class PostAuth (
    val phone: String,
    val password: String,
    @SerializedName("app_version")
    val appVersion: Int
)