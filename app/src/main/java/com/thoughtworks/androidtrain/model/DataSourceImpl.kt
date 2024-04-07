package com.thoughtworks.androidtrain.model

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.thoughtworks.androidtrain.model.dao.TweetDao
import com.thoughtworks.androidtrain.model.entity.Tweet
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


class DataSourceImpl(private val context: Context,private val id: Int, private val tweetDao: TweetDao) : DataSource {

    override suspend fun fetchTweets(): Flowable<List<Tweet>> = withContext(Dispatchers.IO) {

        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://raw.githubusercontent.com/TW-Android-Junior-Training/android_training_practice/main/json/tweets.json")
            .build()
        var networkFlowable: Flowable<List<Tweet>>? = null
        try {
            val response: Response = client.newCall(request).execute()
            val result = response.body!!.string()
            val gson = Gson()
            val tweets =
                gson.fromJson<List<Tweet>>(result, object : TypeToken<List<Tweet>>() {}.type)
            networkFlowable = Flowable.fromCallable { tweets }
                .subscribeOn(Schedulers.io())
            tweetDao.updateTweets(tweets)

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