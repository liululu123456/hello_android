package com.thoughtworks.androidtrain

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.thoughtworks.androidtrain.adapter.CustomAdapter
import com.thoughtworks.androidtrain.model.AppDatabase
import com.thoughtworks.androidtrain.model.DataSourceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class TweetsActivity: AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tweets_layout)
        val recyclerView: RecyclerView = findViewById(R.id.tweets)

        val customAdapter = CustomAdapter(emptyList())
        recyclerView.adapter = customAdapter
        recyclerView.layoutManager = LinearLayoutManager(this@TweetsActivity)

        val dataSource = initDataSource()
        val viewModal = initViewModal(dataSource)

        lifecycleScope.launch(Dispatchers.Main) {
            viewModal.fetchTweetsData()
            viewModal.tweetsLiveData.observe(this@TweetsActivity) { tweets ->
                customAdapter.updateData(tweets)
            }
        }
    }

    private fun initDataSource(): DataSourceImpl {
        val appDatabase = Room.databaseBuilder(this, AppDatabase::class.java, "app_database").build()
        val tweetDao = appDatabase.tweetDao()
        return DataSourceImpl(this@TweetsActivity,tweetDao)

    }
    private fun initViewModal( dataSource : DataSourceImpl): MainViewModal{
        val viewModelFactory = MainViewModalFactory(dataSource)
        return ViewModelProvider(this, viewModelFactory)[MainViewModal::class.java]
    }
}