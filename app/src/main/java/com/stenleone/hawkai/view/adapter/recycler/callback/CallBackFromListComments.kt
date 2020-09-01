package com.stenleone.hawkai.view.adapter.recycler.callback

interface CallBackFromListComments {

    fun likeClick(adapterPosition: Int)
    fun userClick(adapterPosition: Int)
    fun replyClick(adapterPosition: Int)
}