package com.stenleone.hawkai.view.adapter.recycler

import com.stenleone.hawkai.view.adapter.view_pager.util.CustomPageTransformer
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding3.view.clicks
import com.stenleone.hawkai.databinding.NewsItemBinding
import com.stenleone.hawkai.model.data.get.post_news.Result
import com.stenleone.hawkai.view.adapter.recycler.callback.CallBackFromListNews
import com.stenleone.hawkai.view.adapter.view_pager.SlideImageAdapter
import io.reactivex.disposables.CompositeDisposable
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import java.util.concurrent.TimeUnit

class ListNewsRecycler :
    RecyclerView.Adapter<ListNewsRecycler.ViewHolder>(), KoinComponent {

    var arrayView: ArrayList<Result> = ArrayList()
    var listener: CallBackFromListNews? = null

    private val compositeDisposable = CompositeDisposable()

    inner class ViewHolder(private val binding: NewsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                pager.setPageTransformer(CustomPageTransformer())

                if (listener != null) {
                    compositeDisposable.add(
                        joinDiscussion.clicks()
                            .throttleFirst(1, TimeUnit.SECONDS)
                            .subscribe {
                                listener?.joinClick(arrayView[adapterPosition].id)
                            })

                    compositeDisposable.add(
                        userIco.clicks()
                            .throttleFirst(1, TimeUnit.SECONDS)
                            .subscribe {
                                listener?.userClick(arrayView[adapterPosition].id)
                            })
                }
            }
        }

        fun bind(item: Result) {
            binding.apply {
                result = item
                userIcoUrl = item.author.image

                val slideAdapter: SlideImageAdapter by inject()

                if (binding.result?.images != null) {
                    slideAdapter.listImage = binding.result?.images!!
                }

                pager.adapter = slideAdapter
                indicator.setViewPager(pager)
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

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        compositeDisposable.dispose()
    }
}