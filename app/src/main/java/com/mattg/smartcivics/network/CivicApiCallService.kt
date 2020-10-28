package com.mattg.smartcivics.network


import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


object ApiCallService {


    private const val BASE_URL = "https://www.googleapis.com/civicinfo/v2/"
    private const val PRO_BASE_MEMBER_URL = "https://api.propublica.org/congress/v1/"
    private const val COM_URL = "https://api.propublica.org/congress/v1/"
    private const val MEMBER_FINANCE_BASE_URL = "https://api.propublica.org/campaign-finance/v1/"
    private const val MEMBER_BASE_URL = "https://api.propublica.org/congress/v1/members/"
    private const val OPEN_BASE_URL = "https://www.opensecrets.org/api/"
    private const val NEWS_BASE_URL = "https://newsapi.org/v2/"



    private var api: CivicApiCall? = null

    private var apiWiki: WikiImageCall? = null

    private var apiProPub: ProPublicaCall? = null

    private var apiProPubSingle: SingleProPublicaMemeberCall? = null

    private var apiFinance: FinanceCall? = null

    private var apiOpenSecret: OpenSecretCall ?= null

    private var apiNews: NewsApiCall ?= null

    private fun getNewsApiCall(): NewsApiCall {
        if(apiNews == null){
            val okHttpClient = OkHttpClient.Builder()
            apiNews = Retrofit.Builder()
                .baseUrl(NEWS_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient.build())
                .build()
                .create(NewsApiCall::class.java)
        }
        return apiNews!!
    }

    private fun getGenericProPublica(): ProPublicaCall {
        if(apiProPub == null) {
            val okHttpClient = OkHttpClient.Builder()

            apiProPub = Retrofit.Builder()
                .baseUrl(MEMBER_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient.build())
                .build()
                .create(ProPublicaCall::class.java)
        }
        return apiProPub!!

    }

    private fun getOpenSecretApi(): OpenSecretCall{
        if(apiOpenSecret == null){
            val okHttpClient = OkHttpClient.Builder()
            okHttpClient.addInterceptor(object: Interceptor{
                override fun intercept(chain: Interceptor.Chain): Response {
                    val original = chain.request()
                    val requestBuilder = original.newBuilder()
                        .header("Content-Type", "text/html; charset=UTF-8")
                    val request = requestBuilder.build()
                    return chain.proceed(request)
                }
            })
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            okHttpClient.addInterceptor(logging)
            okHttpClient.interceptors().add(logging)

            apiOpenSecret = Retrofit.Builder()
                .baseUrl(OPEN_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(OpenSecretCall::class.java)
        }
        return apiOpenSecret!!
    }


    private fun getFromUrl() : ProPublicaCall {
        if(apiProPub == null){
            val okHttpClient = OkHttpClient.Builder()

            apiProPub = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient.build())
                .build()
                .create(ProPublicaCall::class.java)
        }
        return apiProPub!!
    }

    private fun getCommitteeDetails(): ProPublicaCall {
        if(apiProPub == null){
            val okHttpClient = OkHttpClient.Builder()


            apiProPub = Retrofit.Builder()
                .baseUrl(COM_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient.build())
                .build()
                .create(ProPublicaCall::class.java)
        }
        return apiProPub!!
    }


    private fun getMemberFinance(): FinanceCall {
        if(apiFinance == null){
            val okHttpClient = OkHttpClient.Builder()

            apiFinance = Retrofit.Builder()
                .baseUrl(MEMBER_FINANCE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient.build())
                .build()
                .create(FinanceCall::class.java)
        }
        return apiFinance!!
    }


    private fun getSingleProPubApi() : SingleProPublicaMemeberCall {
        if(apiProPubSingle == null) {

            val okHttpClient = OkHttpClient.Builder()

            apiProPubSingle = Retrofit.Builder()
                .baseUrl(PRO_BASE_MEMBER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient.build())
                .build()
                .create(SingleProPublicaMemeberCall::class.java)
        }
        return apiProPubSingle!!
    }
    private fun getCivicApi(): CivicApiCall {

        if (api == null) {

            val okHttpClient = OkHttpClient.Builder()

            api = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient.build())
                .build()
                .create(CivicApiCall::class.java)
                Log.i("CALL", "$api")
        }

        return api!!
    }

    private fun getProListAllMembers() : ProPublicaCall {
        if(apiProPub == null) {
            val okHttpClient = OkHttpClient.Builder()

            apiProPub = Retrofit.Builder()
                .baseUrl(PRO_BASE_MEMBER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient.build())
                .build()
                .create(ProPublicaCall::class.java)
            Log.i("CALL", "$apiWiki")
        }
        return apiProPub!!

    }

    //for details on selected committee
    fun getCommitteeDetail() = getCommitteeDetails()
    //for getting google civic api call
    fun get() = getCivicApi()
    //for getting a members financials
    fun getMemberFinances() = getMemberFinance()
    //for wikipedia api
 //   fun getWiki() = getWikiApi()
    //for getting full list of congressional members proPublica (to merge with civic api call to create 'representative' class)
    fun getProAll() = getProListAllMembers()
    //for getting single member from proPublica
    fun getProSingle() = getSingleProPubApi()
    //for getting committee with url
    fun getCommitteeWithUrl() = getFromUrl()
    //for getting subcommittee with url
    fun getSubCommitteeWithUrl() = getFromUrl()
    //for bill from url
    fun getBillFromUrl() = getFromUrl()
    //generic call
    fun getProApi() = getGenericProPublica()
    //getOpenSecret
    fun getOpenApi() = getOpenSecretApi()
    //get news
    fun getNews() = getNewsApiCall()
}
