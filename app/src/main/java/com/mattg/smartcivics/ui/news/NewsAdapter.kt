package com.mattg.smartcivics.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.mattg.smartcivics.R
import com.mattg.smartcivics.databinding.NewsItemBinding
import com.mattg.smartcivics.models.news.Article
import com.mattg.smartcivics.ui.home.NewsClickListener

class NewsAdapter(private val articles: List<Article>, private val clickListener: NewsClickListener) :
RecyclerView.Adapter<NewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(articles[position], clickListener)
    }

    override fun getItemCount(): Int = articles.size
}

class NewsViewHolder(private val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root){
companion object {
    fun from(parent: ViewGroup) : NewsViewHolder{
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = NewsItemBinding.inflate(layoutInflater, parent, false)
        return NewsViewHolder(binding)
    }
}
    fun bind(newsArticle: Article, clickListener: NewsClickListener){
        binding.article = newsArticle
        binding.root.setOnClickListener {
            clickListener.onClick(newsArticle, adapterPosition)
        }
        Glide.with(binding.ivNewsImage)
            .load(newsArticle.urlToImage)
            .fallback(R.drawable.news_placeholder)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.ivNewsImage)

        binding.executePendingBindings()
    }


}
