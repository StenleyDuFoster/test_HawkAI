package com.stenleone.hawkai.view.recycler

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding3.view.clicks
import com.stenleone.hawkai.model.data.get.post_news.Result
import com.stenleone.hawkai.view.recycler.callback.CallBackFromListNews
import java.util.concurrent.TimeUnit

class ListNewsRecycler : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var arrayView: ArrayList<Result>

    inner class ViewHolder(itemView: View, listener: CallBackFromListNews) :
        RecyclerView.ViewHolder(itemView) {

        init {


            itemView.clicks()
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe {
                    if (listener != null) {
                        val position = adapterPosition
                        if (position != RecyclerView.NO_POSITION) {
                            listener.itemClick(position)
                        }
                    }
                }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {

    }
}