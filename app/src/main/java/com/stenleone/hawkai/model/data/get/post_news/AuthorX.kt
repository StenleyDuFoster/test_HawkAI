package com.stenleone.hawkai.model.data.get.post_news

data class AuthorX(
    val company: String,
    val country: String,
    val default_native_language: String,
    val degrees: List<DegreeX>,
    val email: String,
    val english_first_name: String,
    val english_last_name: String,
    val first_name: String,
    val id: Int,
    val image: String,
    val image_height: Int,
    val image_width: Int,
    val is_pro: Boolean,
    val languages: List<String>,
    val last_name: String,
    val native_first_name: String,
    val native_last_name: String,
    val phone: String,
    val points: Int,
    val specializations: List<String>,
    val title: String
)