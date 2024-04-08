package com.thoughtworks.androidtrain.model.dataSource

import android.content.Context
import android.widget.Toast
import com.thoughtworks.androidtrain.model.port.TweetApi
import com.thoughtworks.androidtrain.model.dao.TweetDao
import com.thoughtworks.androidtrain.model.entity.Tweet
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class DataSourceImpl(private val context: Context, private val tweetDao: TweetDao) : DataSource {
    private val BASE_URL = "https://raw.githubusercontent.com/TW-Android-Junior-Training/android_training_practice/main/json/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    override suspend fun fetchTweets(): Flowable<List<Tweet>> = withContext(Dispatchers.IO) {
        var networkFlowable: Flowable<List<Tweet>?>? = null
        try {
            val response = retrofit.create(TweetApi::class.java).getTweets().body()
            networkFlowable = Flowable.fromCallable { response }
                .subscribeOn(Schedulers.io())
            tweetDao.updateTweets(response!!)
        } catch (e: IOException) {
            e.printStackTrace()
            handleNetworkError(e)
        }
        val tweetsFromDB = tweetDao.getAllTweets()
        return@withContext Flowable.merge(tweetsFromDB, networkFlowable)
    }
    private suspend fun handleNetworkError(e: IOException) = withContext(Dispatchers.Main) {
        withContext(Dispatchers.Main) {
            println(e.message)
            Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }
}