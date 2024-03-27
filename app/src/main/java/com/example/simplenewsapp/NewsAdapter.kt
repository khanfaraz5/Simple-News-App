package com.example.simplenewsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.simplenewsapp.model.NewsArticleModel

class NewsAdapter:RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private val newsItems: ArrayList<NewsArticleModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {

        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.news_item, parent, false)

        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val title = newsItems.get(position).title
        val desc = newsItems.get(position).description

        holder.textViewTitle.text = title
        holder.textViewDesc.text = desc
    }

    override fun getItemCount(): Int {
        return newsItems.size
    }

    fun updateList(updatedList: ArrayList<NewsArticleModel>) {
        newsItems.clear()
        newsItems.addAll(updatedList)

        notifyDataSetChanged()
    }

    class NewsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val textViewTitle: TextView = itemView.findViewById(R.id.text_view_topic)
        val textViewDesc: TextView = itemView.findViewById(R.id.text_view_desc)
    }
}