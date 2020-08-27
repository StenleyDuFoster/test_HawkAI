package com.stenleone.hawkai.model.data.get.login

import com.google.gson.annotations.SerializedName

data class User(
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
    @SerializedName("is_pro")
    val isPro: Boolean,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("native_first_name")
    val nativeFirstName: String,
    @SerializedName("native_last_name")
    val nativeLastName: String,
    val phone: String,
    val points: Int
)