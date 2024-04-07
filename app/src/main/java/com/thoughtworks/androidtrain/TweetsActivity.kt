package com.thoughtworks.androidtrain

import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import androidx.annotation.RawRes
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.thoughtworks.androidtrain.adapter.CustomAdapter
import com.thoughtworks.androidtrain.model.AppDatabase
import com.thoughtworks.androidtrain.model.DataSourceImpl
import com.thoughtworks.androidtrain.model.entity.Tweet
import io.reactivex.Flowable
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.io.IOException


class TweetsActivity: AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tweets_layout)
        val recyclerView: RecyclerView = findViewById(R.id.tweets)

        val appDatabase = Room.databaseBuilder(this, AppDatabase::class.java, "app_database").build()
        val tweetDao = appDatabase.tweetDao()
        val dataSource = DataSourceImpl(this@TweetsActivity,R.raw.tweets,tweetDao)
        var filteredTweets: Flowable<List<Tweet>> = Flowable.empty()
        lifecycleScope.launch{
            filteredTweets = dataSource.fetchTweets().map { tweets ->
                tweets.filter { it.content != null }
            }
            val customAdapter = CustomAdapter(filteredTweets.blockingFirst())
            recyclerView.adapter = customAdapter
            recyclerView.layoutManager = LinearLayoutManager(this@TweetsActivity)
        }

    }
}