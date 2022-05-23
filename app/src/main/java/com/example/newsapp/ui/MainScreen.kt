package com.example.newsapp.ui

import android.content.ClipData.newIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.adapter.NewsAdapter
import com.example.newsapp.dataclass.Article
import com.example.newsapp.network.ApiRepository

class MainScreen : AppCompatActivity() , NewsAdapter.OnItemClickListner {

    lateinit var newsAdapter: NewsAdapter
    lateinit var newsrecyclerview : RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)
        supportActionBar?.hide()
        newsrecyclerview = findViewById(R.id.rview)

        newsrecyclerview.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        newsAdapter = NewsAdapter(listOf(),this)
        newsrecyclerview.adapter = newsAdapter

        ApiRepository.getNewsData(
            onSuccess = ::onNewsDataFetch,
            onError1 = ::onError1,
            onError2 = ::onError2,
            onError3 = ::onError3
        )


    }
    private fun onNewsDataFetch(movies: List<Article>) {
        newsAdapter.updateData(movies)
    }

    private fun onError1() {
        Toast.makeText(this, "error found", Toast.LENGTH_SHORT).show()
    }

    private fun onError2() {
        Toast.makeText(this, "error fetch", Toast.LENGTH_SHORT).show()
    }

    private fun onError3() {
        Toast.makeText(this, "error occure", Toast.LENGTH_SHORT).show()
    }

    override fun onItemClick(item: Article, position: Int) {
        val intent = Intent(this,NewsDetails::class.java)
        intent.putExtra("title", item.title)
        intent.putExtra("description", item.description)
        intent.putExtra("content", item.content)
        intent.putExtra("publishedAt", item.publishedAt)
        intent.putExtra("urlToImage", item.urlToImage)
        startActivity(intent)
    }


}