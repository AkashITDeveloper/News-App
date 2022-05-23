package com.example.newsapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.newsapp.R
import com.example.newsapp.dataclass.Article

class NewsAdapter(private var news: List<Article> , var clickListner: OnItemClickListner) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    class ViewHolder(IteamView : View) : RecyclerView.ViewHolder(IteamView){
        val tittle : TextView = IteamView.findViewById(R.id.main_tittle)
        val description : TextView = IteamView.findViewById(R.id.main_description)
        private val img  : ImageView= IteamView.findViewById(R.id.mainimage)

        fun Bind(news: Article){
            Glide.with(itemView)
                .load(news.urlToImage)
                .transform(CenterCrop())
                .into(img)
            tittle.text = news.title
            description.text = news.description
        }
        fun initialize(item: Article, action: OnItemClickListner) {
            itemView.setOnClickListener{
                action.onItemClick(item,adapterPosition)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_design,parent,false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.Bind(news[position])
        holder.initialize(news.get(position),clickListner)
    }

    override fun getItemCount() : Int = news.size

    fun updateData(news: List<Article>) {
        this.news = news
        notifyDataSetChanged()
    }
    interface OnItemClickListner {
        fun onItemClick(item: Article, position: Int)
    }
}