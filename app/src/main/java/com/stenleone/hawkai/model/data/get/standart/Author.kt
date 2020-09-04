package com.stenleone.hawkai.model.data.get.standart

import com.google.gson.annotations.SerializedName

data class Author(
    val company: String,
    val country: String,
    @SerializedName("default_native_language")
    val defaultNativeLanguage: String,
    val degrees: List<Any>,
    val email: String,
    @SerializedName("english_first_name")
    val englishFirstName: String,
    @SerializedName("english_last_name")
    val englishLastName: String,
    @SerializedName("first_name")
    val firstName: String,
    val id: Int,
    val image: String,
    @SerializedName("image_height")
    val imageHeight: Int,
    @SerializedName("image_width")
    val imageWidth: Int,
    @SerializedName("is_pro")
    val isPro: Boolean,
    val languages: List<String>,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("native_first_name")
    val nativeFirstName: String,
    @SerializedName("native_last_name")
    val nativeLastName: String,
    val phone: String,
    val points: Int,
    val specializations: List<String>,
    val title: String
)