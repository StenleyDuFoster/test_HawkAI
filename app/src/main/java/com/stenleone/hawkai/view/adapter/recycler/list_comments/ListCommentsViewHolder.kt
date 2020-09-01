package com.stenleone.hawkai.view.adapter.recycler.list_comments

import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.view.longClicks
import com.stenleone.hawkai.databinding.CommentsItemBinding
import com.stenleone.hawkai.model.data.get.comments.Result
import com.stenleone.hawkai.util.pop_many.TextCopyPopMany
import com.stenleone.hawkai.view.adapter.recycler.callback.CallBackFromListComments
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

class ListCommentsViewHolder(
    private val binding: CommentsItemBinding,
    listener: CallBackFromListComments?
) :
    RecyclerView.ViewHolder(binding.root) {

    val compositeDisposable = CompositeDisposable()

    init {
        binding.apply {
            if (listener != null) {

                compositeDisposable.add(
                    imageUser.clicks()
                        .throttleFirst(1, TimeUnit.SECONDS)
                        .subscribe {
                            listener?.userClick(adapterPosition)
                        }
                )

                compositeDisposable.add(
                    replyClick.clicks()
                        .throttleFirst(1, TimeUnit.SECONDS)
                        .subscribe {
                            listener?.replyClick(adapterPosition)
                        }
                )

                compositeDisposable.add(
                    likeClick.clicks()
                        .throttleFirst(1, TimeUnit.SECONDS)
                        .subscribe {
                            listener?.likeClick(adapterPosition)
                        }
                )

                compositeDisposable.add(
                    textComment.longClicks()
                        .throttleFirst(1,TimeUnit.SECONDS)
                        .subscribe {
                            TextCopyPopMany(textComment)
                        }
                )
            }
        }
    }

    fun bind(item: Result) {
        binding.apply {
            res = item
            imageProfileIco = item.author.image
            date = item.create_date
        }
    }

    fun setLayVisible(visible: Int) {
        binding.layParent.visibility = visible
    }
}