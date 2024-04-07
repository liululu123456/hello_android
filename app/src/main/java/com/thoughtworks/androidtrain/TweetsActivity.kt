package com.thoughtworks.androidtrain

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.thoughtworks.androidtrain.adapter.CustomAdapter
import com.thoughtworks.androidtrain.model.AppDatabase
import com.thoughtworks.androidtrain.model.DataSourceImpl
import com.thoughtworks.androidtrain.model.entity.Tweet
import io.reactivex.Flowable
import kotlinx.coroutines.launch


class TweetsActivity: AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tweets_layout)
        val recyclerView: RecyclerView = findViewById(R.id.tweets)

        val appDatabase = Room.databaseBuilder(this, AppDatabase::class.java, "app_database").build()
        val tweetDao = appDatabase.tweetDao()

        val dataSource = DataSourceImpl(this@TweetsActivity,tweetDao)
        var filteredTweets: Flowable<List<Tweet>> = Flowable.empty()

        lifecycleScope.launch {
            filteredTweets = dataSource.fetchTweets().map { tweets ->
                tweets.filter { it.content != null }
            }
            val customAdapter = CustomAdapter(filteredTweets.blockingFirst())
            recyclerView.adapter = customAdapter
            recyclerView.layoutManager = LinearLayoutManager(this@TweetsActivity)
        }
    }
}