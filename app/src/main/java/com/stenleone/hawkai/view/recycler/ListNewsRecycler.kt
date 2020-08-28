package com.stenleone.hawkai.view.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding3.view.clicks
import com.stenleone.hawkai.databinding.NewsItemBinding
import com.stenleone.hawkai.model.data.get.post_news.Result
import com.stenleone.hawkai.view.recycler.callback.CallBackFromListNews
import java.util.concurrent.TimeUnit

class ListNewsRecycler :
    RecyclerView.Adapter<ListNewsRecycler.ViewHolder>() {

    var arrayView: ArrayList<Result> = ArrayList()
    var listener: CallBackFromListNews? = null

    inner class ViewHolder(val binding: NewsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Result) {
            binding.apply {
                result = item
                userIcoUrl = item.author.image

                if (listener != null) {
                    joinDiscussion.clicks()
                        .throttleFirst(1, TimeUnit.SECONDS)
                        .subscribe {
                            listener?.userClick()
                        }

                    userIco.clicks()
                        .throttleFirst(1, TimeUnit.SECONDS)
                        .subscribe {
                            listener?.userClick()
                        }

                    userImage.clicks()
                        .throttleFirst(1, TimeUnit.SECONDS)
                        .subscribe {
                            listener?.imageClick()
                        }
                }

                if (item.images.size > 0) {
                    userImageUrl = item.images[0].image
                } else {
                    userImageUrl = null
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NewsItemBinding.inflate(inflater)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemModel = arrayView[position]

        holder.bind(itemModel)
    }

    override fun getItemCount(): Int {
        return arrayView.size
    }
}