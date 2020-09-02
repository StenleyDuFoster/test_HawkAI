package com.stenleone.hawkai.view.adapter.recycler.list_comments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView

import com.stenleone.hawkai.databinding.CommentsItemBinding
import com.stenleone.hawkai.model.data.get.comments.Result
import com.stenleone.hawkai.util.easyInfo.makeToast
import com.stenleone.hawkai.view.adapter.recycler.ListNewsRecycler
import com.stenleone.hawkai.view.adapter.recycler.callback.CallBackFromListComments

class ListCommentsRecycler :
    RecyclerView.Adapter<ListCommentsViewHolder>() {

    var arrayView: ArrayList<Result> = ArrayList()
    var listener: CallBackFromListComments? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListCommentsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CommentsItemBinding.inflate(inflater)

        return ListCommentsViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holderListComments: ListCommentsViewHolder, position: Int) {

        val itemModel = arrayView[position]
        holderListComments.setLayVisible(getItemViewType(position))
        holderListComments.bind(itemModel)
    }

    override fun getItemCount(): Int {
        return arrayView.size
    }

    override fun getItemViewType(position: Int): Int {
        if (arrayView[position].parent == null) {
            return View.GONE
        } else {
            return View.VISIBLE
        }
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        ListCommentsViewHolder.compositeDisposable.dispose()
    }
}