package com.example.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.dataclass.Article
import org.w3c.dom.Text

class NewsDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)
        supportActionBar?.hide()

        val dateTime: TextView = findViewById(R.id.publish_time_date)
        val tittle: TextView = findViewById(R.id.detials_tittle_text)
        val content : TextView = findViewById(R.id.detials_content_text)
        val image: ImageView = findViewById(R.id.detials_image_view)

        tittle.text = getIntent().getStringExtra("title")
        content.text = getIntent().getStringExtra("content")
        dateTime.text = getIntent().getStringExtra("publishedAt")
        Glide.with(this).load(""+ getIntent().getStringExtra("urlToImage")).into(image)

    }
}