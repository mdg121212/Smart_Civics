package com.mattg.smartcivics.ui.news.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mattg.smartcivics.R
import com.mattg.smartcivics.models.news.Article
import com.mattg.smartcivics.ui.home.NewsClickListener
import com.mattg.smartcivics.ui.news.NewsAdapter
import com.mattg.smartcivics.ui.news.NewsViewModel
import com.mattg.smartcivics.utils.Constants
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment : Fragment() {

    private lateinit var newsViewModel: NewsViewModel
    private lateinit var newsClickListener: NewsClickListener

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        newsViewModel =
                ViewModelProvider(requireActivity()).get(NewsViewModel::class.java)
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsViewModel.getBasicNews( Constants.newsKey)
        observeViewModel()
    }

    private fun observeViewModel() {
        newsViewModel.apply {
            progressShowing.observe(viewLifecycleOwner){
              loadingDisplay(it)
            }
        }
        newsViewModel.articles.observe(viewLifecycleOwner){
            if(it != null){
                newsClickListener = NewsClickListener{ article, _ ->
                    newsViewModel.setArticle(article)
                    findNavController().navigate(R.id.action_navigation_notifications_to_articleFragment)
                }
                initRecycler(it)
            }
        }
    }

    private fun initRecycler(list: List<Article>){
      val newsAdapter = NewsAdapter(list, newsClickListener)
        val newsLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rv_news.apply {
            adapter = newsAdapter
            layoutManager = newsLayoutManager
        }
    }

    private fun loadingDisplay(loadingValue: Boolean){
        when(loadingValue){
            true -> {
                progressBar_news.visibility = View.VISIBLE
                rv_news.visibility = View.INVISIBLE
            }
            false -> {
                progressBar_news.visibility = View.INVISIBLE
                rv_news.visibility = View.VISIBLE
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.news_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
        R.id.senate_menu -> {
            newsViewModel.clearList()
            newsViewModel.getSenateNews(Constants.newsKey)
        }
        R.id.congress_menu -> {
            newsViewModel.clearList()
            newsViewModel.getCongressNews(Constants.newsKey)
        }
        R.id.general_menu -> {
            newsViewModel.clearList()
            newsViewModel.getBasicNews(Constants.newsKey)
        }
        }
        return super.onOptionsItemSelected(item)
    }

    }