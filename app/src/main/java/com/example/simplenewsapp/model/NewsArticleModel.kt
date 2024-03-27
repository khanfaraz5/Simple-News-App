package com.example.simplenewsapp.model

data class NewsArticleModel(
    val content: String,
    val description: String,
    val image: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String
)