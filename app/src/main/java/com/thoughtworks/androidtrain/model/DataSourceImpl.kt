package com.thoughtworks.androidtrain.model

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.thoughtworks.androidtrain.R
import com.thoughtworks.androidtrain.model.dao.TweetDao
import com.thoughtworks.androidtrain.model.entity.Tweet
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import java.io.BufferedReader
import java.io.InputStreamReader

class DataSourceImpl(private val context: Context,private val id: Int, private val tweetDao: TweetDao) : DataSource {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun fetchTweets(): Flowable<List<Tweet>> {
        val jsonFlowable = readJsonFromRaw(context)
            .map { json ->
                val type = object : TypeToken<List<Tweet>>() {}.type
                Gson().fromJson<List<Tweet>>(json, type)
            }
            .doOnNext { tweets ->
                tweetDao.updateTweets(tweets)
            }
            .subscribeOn(Schedulers.io())
        println(jsonFlowable.blockingFirst())
        val tweetsFromDB = tweetDao.getAllTweets()

        return Flowable.merge(tweetsFromDB, jsonFlowable)
    }

    private fun readJsonFromRaw(context: Context): Flowable<String> {
        return Flowable.fromCallable {
            val inputStream = context.resources.openRawResource(id)
            val reader = BufferedReader(InputStreamReader(inputStream))
            val stringBuilder = StringBuilder()
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                stringBuilder.append(line)
            }
            reader.close()
            stringBuilder.toString()
        }
    }
//    private fun readJsonFromRaw(): Flowable<String> {
//        return Flowable.fromCallable {
//            val inputStream = context.resources.openRawResource(R.raw.tweets)
//            val reader = BufferedReader(InputStreamReader(inputStream))
//            val stringBuilder = StringBuilder()
//            var line: String?
//            while (reader.readLine().also { line = it } != null) {
//                stringBuilder.append(line)
//            }
//            reader.close()
//            stringBuilder.toString()
//        }
//    }
}