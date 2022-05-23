package com.example.newsapp.network

import com.example.newsapp.dataclass.NewsData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top-headlines")
    fun getDataFromApi(
        @Query("country")   country: String,
        @Query("apiKey") apiKey: String = "65186c644f47495d88297dd665e1b033",
    ): Call<NewsData>
}