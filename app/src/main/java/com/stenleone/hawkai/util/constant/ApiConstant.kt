package com.stenleone.hawkai.util.constant

object ApiConstant {

    const val BASE_URL = "https://dev.hawkaiapp.com/"

    private const val API = "api"

    const val LOGIN_URL = "$API/auth/login"
    const val POST_URL = "$API/post/"
    const val COMMENT_URL = "$POST_URL{post_number}/comment/"

    const val AUTH = "Authorization"
    const val TOKEN_PREFIX = "JWT"

    const val PHONE = "%2B380666666666"
    const val ERROR_TEXT = "error code "
}