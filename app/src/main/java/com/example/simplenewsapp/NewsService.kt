package com.example.simplenewsapp

import com.example.simplenewsapp.model.NewsModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// https://gnews.io/api/v4/top-headlines?category=general&lang=en&country=us&max=10&apikey=37dfaf25be80eadf1576b03b06f5b7f3

const val BASE_URL = "https://gnews.io/api/v4/"
const val API_KEY = "37dfaf25be80eadf1576b03b06f5b7f3"

interface NewsInterface {

    @GET("top-headlines?token=$API_KEY")
    fun getBreakingNews(
        @Query("lang") lang: String,
        @Query("country") country: String,
        @Query("topic") topic: String,
        @Query("page") page: Int
    ): Call<NewsModel>

}

object NewsService {
    val newsInterface: NewsInterface

    init {
        val retrofit = Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        newsInterface = retrofit.create(NewsInterface::class.java)
    }
}