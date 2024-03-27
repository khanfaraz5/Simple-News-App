package com.example.simplenewsapp.model

data class NewsModel(
    val articles: List<NewsArticleModel>,
    val totalArticles: Int
)