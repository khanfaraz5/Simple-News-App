package com.example.simplenewsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.simplenewsapp.model.NewsArticleModel
import com.example.simplenewsapp.model.NewsModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var newsItems: ArrayList<NewsArticleModel>
    lateinit var adapter: NewsAdapter

    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    private fun initViews() {

        progressBar = findViewById(R.id.progress_bar_main)

        adapter = NewsAdapter()
        recyclerView = findViewById(R.id.recycler_view_news)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        newsItems = ArrayList()
        getNews()

    }

    private fun getNews() {
        progressBar.visibility = View.VISIBLE

        val news = NewsService.newsInterface.getBreakingNews("en", "in", "breaking-news", 1)

        news.enqueue(object : Callback<NewsModel> {

            override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {
                val news: NewsModel? = response.body()

                if (news != null) {

                    newsItems = news.articles as ArrayList<NewsArticleModel>
                    adapter.updateList(newsItems)

                }

                progressBar.visibility = View.GONE
            }

            override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                Toast.makeText(applicationContext, "Something went wrong", Toast.LENGTH_SHORT)
                    .show()
                progressBar.visibility = View.GONE
            }
        })

    }

}