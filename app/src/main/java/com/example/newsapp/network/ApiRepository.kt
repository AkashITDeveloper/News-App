package com.example.newsapp.network

import android.util.Log
import com.example.newsapp.dataclass.Article
import com.example.newsapp.dataclass.NewsData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiRepository {

    private val api: ApiService
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(ApiService::class.java)
    }

    fun getNewsData(
        Country: String = "in",
        onSuccess: (movies: List<Article>) -> Unit,
        onError1: () -> Unit,
        onError2:()   -> Unit,
        onError3:()   -> Unit
    ) {
        api.getDataFromApi(country = Country)
            .enqueue(object : Callback<NewsData> {
                override fun onResponse(
                    call: Call<NewsData>,
                    response: Response<NewsData>
                ) {
                    if (response.isSuccessful) {
                        Log.d("Response", "success")
                        val responseBody = response.body()

                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.articles)
                        } else {
                            onError1.invoke()
                        }
                    } else {
                        onError2.invoke()
                    }
                }

                override fun onFailure(call: Call<NewsData>, t: Throwable) {
                    onError3.invoke()
                }
            })
    }

}