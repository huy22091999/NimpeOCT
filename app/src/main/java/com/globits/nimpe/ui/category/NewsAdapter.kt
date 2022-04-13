package com.globits.nimpe.ui.category

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.globits.nimpe.data.model.News
import com.globits.nimpe.databinding.ItemNewLayoutBinding
import java.text.SimpleDateFormat

class NewsAdapter(
    val context: Context
) :
    PagingDataAdapter<News, NewsAdapter.NewsViewHolder>(COMPARATOR) {

    class NewsViewHolder(val context: Context,val binding: ViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindHeath(news: News) {
            with(binding as ItemNewLayoutBinding){
               title.text=news.title
                val sdf = SimpleDateFormat("hh:mm dd/M/yyyy")
                val currentDate = sdf.format(news.publishDate)
                time.text= currentDate
            }
        }
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bindHeath(it)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewsViewHolder {
        val itemBinding:ViewBinding=ItemNewLayoutBinding.inflate(LayoutInflater.from(context),parent,false)
        return NewsViewHolder(context,itemBinding)
    }
    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<News>() {
            override fun areItemsTheSame(oldItem: News, newItem: News): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: News, newItem: News): Boolean =
                oldItem == newItem

        }
    }
}