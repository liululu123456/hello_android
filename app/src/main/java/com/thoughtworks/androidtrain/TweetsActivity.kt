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
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.thoughtworks.androidtrain.adapter.CustomAdapter
import com.thoughtworks.androidtrain.model.Tweet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.IOException


class TweetsActivity: AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tweets_layout)
        val recyclerView: RecyclerView = findViewById(R.id.tweets)


        lifecycleScope.launch(Dispatchers.IO) {
            val tweetsFromRaw = getTweetsFromRaw(this@TweetsActivity, R.raw.tweets)
            val tweets = tweetsFromRaw.filter { it.content != null }

            withContext(Dispatchers.Main) {
                val customAdapter = CustomAdapter(tweets)
                recyclerView.adapter = customAdapter
                recyclerView.layoutManager = LinearLayoutManager(this@TweetsActivity)

            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun getTweetsFromRaw(context : Context, @RawRes id: Int): List<Tweet>{
        val tweets = mutableListOf<Tweet>()
        try {
            val gson = Gson()
            val r: Resources = context.resources
            val json = r.openRawResource(id)
            var i = json.read()
            val baos = ByteArrayOutputStream()
            while (i != -1) {
                baos.write(i)
                i = json.read()
            }
            json.close()
            val jsonString = baos.toString()

            val tweetListType = object : TypeToken<List<Tweet>>() {}.type
            tweets.addAll(gson.fromJson(jsonString, tweetListType))

        } catch (e: IOException) {
            e.printStackTrace()
        }

        return tweets

    }



}

