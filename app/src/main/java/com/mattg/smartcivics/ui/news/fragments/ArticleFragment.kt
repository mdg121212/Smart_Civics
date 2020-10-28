package com.mattg.smartcivics.ui.news.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.mattg.smartcivics.R
import com.mattg.smartcivics.models.news.Article
import com.mattg.smartcivics.ui.news.NewsViewModel
import kotlinx.android.synthetic.main.fragment_article.*
import kotlinx.android.synthetic.main.news_item.*

class ArticleFragment : Fragment() {

    private lateinit var newsViewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        newsViewModel = ViewModelProvider(requireActivity()).get(NewsViewModel::class.java)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel() {
        newsViewModel.apply {
            article.observe(viewLifecycleOwner){
                setupView(it)
            }
        }
    }

    private fun setupView(it: Article?) {
    if(it != null){
        var contentText: String = ""
        val bodyText = it.content
        val chunkedText = bodyText?.chunked(100)
        chunkedText?.forEach {
            contentText += it
        }
        Log.i("TEXTGIVEN", "${it.content}")

        tv_article_title.text = it.title
       // tv_article_content.text = bodyText
        tv_article_content.text = contentText
        tv_article_source.text = it.source?.name
        tv_article_author.text = it.author
        Glide.with(requireContext())
            .load(it.urlToImage)
            .into(iv_article_fragment)
    }
    }
}