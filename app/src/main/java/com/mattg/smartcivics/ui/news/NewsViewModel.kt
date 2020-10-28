package com.mattg.smartcivics.ui.news

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mattg.smartcivics.models.news.Article
import com.mattg.smartcivics.models.news.NewsResponse
import com.mattg.smartcivics.network.ApiCallService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is News Fragment.....under construction :)"
    }
    val text: LiveData<String> = _text

    private val _articles = MutableLiveData<List<Article>>()
    val articles : LiveData<List<Article>> = _articles

    private val _article = MutableLiveData<Article>()
    val article: LiveData<Article> = _article

    private val _progressShowing = MutableLiveData<Boolean>()
    val progressShowing: LiveData<Boolean> = _progressShowing

    private val _recyclerShowing = MutableLiveData<Boolean>()
    val recyclerShowing: LiveData<Boolean> = _recyclerShowing

    fun getBasicNews(key: String){
        setLoading(true)
        clearList()
        val newsCall = ApiCallService.getNews()
        newsCall.getBasicPoliticsNews(key).enqueue(object: Callback<NewsResponse>{
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                val result = response.body()
                _articles.value = result?.articles
                Log.i("NewsResult", "$result")
                setLoading(false)
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.d("NewsViewModel", t.localizedMessage)
            }
        })
    }

    fun getSenateNews(key: String) {
        setLoading(true)
        clearList()
        val senateNews = ApiCallService.getNews()
        senateNews.getSenateNews(key).enqueue(object: Callback<NewsResponse>{
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                val result = response.body()
                _articles.value = result?.articles
                Log.i("NewsResult", "$result")
                setLoading(false)
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.d("NewsViewModel", t.localizedMessage)
            }
        })
    }

    fun getCongressNews(key: String) {
        setLoading(true)
        clearList()
        val congressNews = ApiCallService.getNews()
        congressNews.getCongressNews(key).enqueue(object: Callback<NewsResponse>{
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                val result = response.body()
                _articles.value = result?.articles
                Log.i("NewsResult", "$result")
                setLoading(false)
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.d("NewsViewModel", t.localizedMessage)
            }
        })
    }

    private fun setLoading(value: Boolean){
        when(value){
            true -> {
                _progressShowing.value = true
                _recyclerShowing.value = false
            }
            false -> {
                _progressShowing.value = false
                _recyclerShowing.value = true
            }
        }

    }

    fun clearList(){
        _articles.value = null
    }

    fun setArticle(article: Article){
        _article.value = article
    }
}